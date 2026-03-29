package ru.nugumanov.price_tracker.core.repository;

import ru.nugumanov.price_tracker.jooq.tables.pojos.TelegramUserPojo;
import ru.nugumanov.price_tracker.jooq.tables.records.TelegramUserRecord;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с пользователями телеграм бота
 */
public interface TelegramUserRepository {

    /**
     * Получение пользователя по чат ID
     *
     * @param chatId Чат ID пользователя
     * @return Пользователь телеграм бота
     */
    Optional<TelegramUserPojo> get(String chatId);

    /**
     * Получение всех активных пользователей бота
     *
     * @return Список активных пользователей телеграм бота
     */
    List<TelegramUserPojo> getActive();

    /**
     * Создание пользователя телеграм бота
     *
     * @param record Пользователь телеграм бота
     */
    void save(TelegramUserRecord record);

    /**
     * Удаление пользователя телеграм бота
     *
     * @param chatId Чат ID пользователя
     */
    void delete(String chatId);
}
