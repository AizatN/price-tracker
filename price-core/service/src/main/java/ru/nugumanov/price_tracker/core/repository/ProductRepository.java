package ru.nugumanov.price_tracker.core.repository;

import ru.nugumanov.price_tracker.jooq.tables.pojos.ProductPojo;
import ru.nugumanov.price_tracker.jooq.tables.records.ProductRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с продуктами
 */
public interface ProductRepository {

    /**
     * Поиск всех продуктов
     *
     * @return Список продуктов
     */
    List<ProductPojo> get();

    /**
     * Поиск продукта по идентификатору
     *
     * @param id Идентификатор продукта
     * @return Optional с продуктом
     */
    Optional<ProductPojo> get(UUID id);

    /**
     * Сохранить продукт
     *
     * @param record Продукт
     */
    void save(ProductRecord record);
}
