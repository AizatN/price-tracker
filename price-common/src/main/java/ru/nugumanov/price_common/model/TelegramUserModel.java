package ru.nugumanov.price_common.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Модель пользователя телеграм бота
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUserModel {

    private String chatId;
    private LocalDateTime createdDt;
    private LocalDateTime deletedDt;
}
