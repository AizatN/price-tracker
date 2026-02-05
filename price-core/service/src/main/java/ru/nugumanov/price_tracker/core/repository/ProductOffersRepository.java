package ru.nugumanov.price_tracker.core.repository;

import ru.nugumanov.price_tracker.jooq.tables.pojos.ProductOffersPojo;

import java.util.List;

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
}
