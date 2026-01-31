package ru.nugumanov.price_tracker.core.repository;

import ru.nugumanov.price_tracker.jooq.tables.pojos.PricePojo;
import ru.nugumanov.price_tracker.jooq.tables.records.PriceRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с ценами
 */
public interface PriceRepository {

    /**
     * Получение всех цен
     *
     * @return Список цен
     */
    List<PricePojo> get();

    /**
     * Получение цены по идентификатору
     *
     * @param id Идентификатор
     * @return Optional с ценой
     */
    Optional<PricePojo> get(UUID id);

    /**
     * Сохранение цены
     *
     * @param record Цена
     */
    void save(PriceRecord record);
}
