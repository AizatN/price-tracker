package ru.nugumanov.price_tracker.notifier.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

@Slf4j
@Component
public class TelegramBotClient {

    private final TelegramClient telegramClient;

    public TelegramBotClient(@Value("${telegram.bot.token}") String botToken) {
        this.telegramClient = new OkHttpTelegramClient(botToken);
    }

    public void sendMessage(String text) {
        var chatIds = resolveChatIds();
        if (chatIds.isEmpty()) {
            log.warn("No chat IDs found. Send /start to the bot first.");
            return;
        }
        for (var chatId : chatIds) {
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

    private List<String> resolveChatIds() {
        try {
            var getUpdates = GetUpdates.builder().build();
            List<Update> updates = telegramClient.execute(getUpdates);
            return updates.stream()
                    .filter(u -> u.getMessage() != null)
                    .map(u -> u.getMessage().getChat().getId().toString())
                    .distinct()
                    .toList();
        } catch (TelegramApiException e) {
            log.error("Failed to get updates: {}", e.getMessage(), e);
        }
        return List.of();
    }
}
