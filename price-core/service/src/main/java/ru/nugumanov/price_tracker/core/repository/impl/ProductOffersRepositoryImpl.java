package ru.nugumanov.price_tracker.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.nugumanov.price_tracker.core.repository.ProductOffersRepository;
import ru.nugumanov.price_tracker.jooq.tables.pojos.ProductOffersPojo;

import java.util.List;
import java.util.Optional;

import static ru.nugumanov.price_tracker.jooq.Tables.PRODUCT_OFFERS;

/**
 * Реализация сервиса для работы с вариантами продажи продукта
 */
@Repository
@RequiredArgsConstructor
public class ProductOffersRepositoryImpl implements ProductOffersRepository {

    private final DSLContext dsl;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductOffersPojo> get() {
        return dsl.selectFrom(PRODUCT_OFFERS)
                .fetchInto(ProductOffersPojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProductOffersPojo> get(Long id) {
        return dsl.selectFrom(PRODUCT_OFFERS)
                .where(PRODUCT_OFFERS.ID.eq(id))
                .fetchOptionalInto(ProductOffersPojo.class);
    }
}
