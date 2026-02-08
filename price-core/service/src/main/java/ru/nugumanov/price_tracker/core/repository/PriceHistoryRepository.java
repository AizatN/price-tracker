package ru.nugumanov.price_tracker.core.repository;

import ru.nugumanov.price_tracker.jooq.tables.records.PriceHistoryRecord;

/**
 * Репозиторий для работы с историей цен
 */
public interface PriceHistoryRepository {

    /**
     * Сохранение истории цены
     *
     * @param record Запись с историей цены
     */
    void save(PriceHistoryRecord record);
}
