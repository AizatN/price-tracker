package ru.nugumanov.price_tracker.core.repository;

import ru.nugumanov.price_tracker.jooq.tables.pojos.ProductOffersPojo;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с вариантами продажи продукта
 */
public interface ProductOffersRepository {

    /**
     * Получение всех вариантов продажи продукта
     *
     * @return Список вариантов продажи продукта
     */
    List<ProductOffersPojo> get();

    /**
     * Получение варианта продажи продукта по идентификатору
     *
     * @param id Идентификатор
     * @return Вариант продажи продукта
     */
    Optional<ProductOffersPojo> get(Long id);
}
