package ru.nugumanov.price_tracker.core.service;

import ru.nugumanov.price_common.model.TelegramUserModel;

import java.util.List;

/**
 * Сервис для работы с пользователями телеграм бота
 */
public interface TelegramUserService {

    /**
     * Получение всех активных пользователей бота
     *
     * @return Список активных пользователей телеграм бота
     */
    List<TelegramUserModel> getActive();

    /**
     * Создание пользователя телеграм бота
     *
     * @param model Пользователь телеграм бота
     */
    void save(TelegramUserModel model);

    /**
     * Удаление пользователя телеграм бота
     *
     * @param chatId Чат ID пользователя
     */
    void delete(String chatId);
}
