package ru.nugumanov.price_tracker.notifier.telegram;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class TelegramBotClient {

    private final TelegramClient telegramClient;
    private final SubscriberRepository subscriberRepository;

    private final Set<String> knownChatIds = new HashSet<>();
    private int lastUpdateOffset = 0;

    public TelegramBotClient(@Value("${telegram.bot.token}") String botToken,
                             SubscriberRepository subscriberRepository) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.subscriberRepository = subscriberRepository;
    }

    @PostConstruct
    public void init() {
        knownChatIds.addAll(subscriberRepository.load());
        log.info("Loaded {} subscribers from Consul", knownChatIds.size());
    }

    public void sendMessage(String text) {
        refreshChatIds();
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

    private void refreshChatIds() {
        try {
            var getUpdates = GetUpdates.builder().offset(lastUpdateOffset).build();
            List<Update> updates = telegramClient.execute(getUpdates);

            boolean changed = false;
            for (Update update : updates) {
                if (update.getMessage() == null) continue;
                String chatId = update.getMessage().getChat().getId().toString();
                String text = update.getMessage().getText();
                if ("/stop".equals(text)) {
                    changed |= knownChatIds.remove(chatId);
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
        } catch (TelegramApiException e) {
            log.error("Failed to get updates: {}", e.getMessage(), e);
        }
    }
}
