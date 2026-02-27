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
        var chatId = resolveChatId();
        if (chatId == null) {
            log.warn("No chat ID found. Send /start to the bot first.");
            return;
        }
        var message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode("Markdown")
                .build();
        try {
            telegramClient.execute(message);
            log.info("Telegram message sent successfully");
        } catch (TelegramApiException e) {
            log.error("Failed to send Telegram message: {}", e.getMessage(), e);
        }
    }

    private String resolveChatId() {
        try {
            var getUpdates = GetUpdates.builder().build();
            List<Update> updates = telegramClient.execute(getUpdates);
            if (!updates.isEmpty()) {
                var lastUpdate = updates.get(updates.size() - 1);
                return lastUpdate.getMessage().getChat().getId().toString();
            }
        } catch (TelegramApiException e) {
            log.error("Failed to get updates: {}", e.getMessage(), e);
        }
        return null;
    }
}
