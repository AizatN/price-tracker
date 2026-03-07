package ru.nugumanov.price_tracker.notifier.telegram;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TelegramBotClient {

    private static final int LONG_POLL_TIMEOUT = 30;

    private final TelegramClient telegramClient;
    private final SubscriberRepository subscriberRepository;

    private final Set<String> knownChatIds = new HashSet<>();
    private int lastUpdateOffset = 0;

    public TelegramBotClient(@Value("${telegram.bot.token}") String botToken,
                             SubscriberRepository subscriberRepository) {
        var httpClient = new OkHttpClient.Builder()
                .readTimeout(LONG_POLL_TIMEOUT + 5, TimeUnit.SECONDS)
                .build();
        this.telegramClient = new OkHttpTelegramClient(httpClient, botToken);
        this.subscriberRepository = subscriberRepository;
    }

    @PostConstruct
    public void init() {
        knownChatIds.addAll(subscriberRepository.load());
        log.info("Loaded {} subscribers from Consul", knownChatIds.size());

        Thread thread = new Thread(this::runLongPolling, "telegram-long-polling");
        thread.setDaemon(true);
        thread.start();
    }

    public void sendMessage(String text) {
        if (knownChatIds.isEmpty()) {
            log.warn("No chat IDs found. Send /start to the bot first.");
            return;
        }
        for (var chatId : knownChatIds) {
            var message = SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .parseMode("Markdown")
                    .build();
            try {
                telegramClient.execute(message);
                log.info("Telegram message sent to {}", chatId);
            } catch (TelegramApiException e) {
                log.error("Failed to send Telegram message to {}: {}", chatId, e.getMessage(), e);
            }
        }
    }

    private void runLongPolling() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                var getUpdates = GetUpdates.builder()
                        .offset(lastUpdateOffset)
                        .timeout(LONG_POLL_TIMEOUT)
                        .build();
                List<Update> updates = telegramClient.execute(getUpdates);
                processUpdates(updates);
            } catch (TelegramApiException e) {
                log.error("Long polling error: {}", e.getMessage(), e);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void processUpdates(List<Update> updates) {
        boolean changed = false;
        for (Update update : updates) {
            if (update.getMessage() == null) continue;
            String chatId = update.getMessage().getChat().getId().toString();
            String text = update.getMessage().getText();
            if ("/stop".equals(text)) {
                boolean removed = knownChatIds.remove(chatId);
                changed |= removed;
                if (removed) {
                    sendReply(chatId, "✅ Вы отписаны от уведомлений!");
                } else {
                    sendReply(chatId, "ℹ️ Вы не были подписаны на рассылку.");
                }
            } else {
                boolean added = knownChatIds.add(chatId);
                changed |= added;
                if ("/start".equals(text)) {
                    if (added) {
                        sendReply(chatId, "✅ Вы подписаны на уведомления о ценах. Отправьте /stop чтобы отписаться.");
                    } else {
                        sendReply(chatId, "ℹ️ Вы уже подписаны на рассылку.");
                    }
                }
            }
        }

        updates.stream()
                .mapToInt(Update::getUpdateId)
                .max()
                .ifPresent(maxId -> lastUpdateOffset = maxId + 1);

        if (changed) {
            subscriberRepository.save(knownChatIds);
        }
    }

    private void sendReply(String chatId, String text) {
        var message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed to send reply to {}: {}", chatId, e.getMessage(), e);
        }
    }
}
