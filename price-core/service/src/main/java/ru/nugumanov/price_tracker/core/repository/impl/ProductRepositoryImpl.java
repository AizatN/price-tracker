package ru.nugumanov.price_tracker.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.nugumanov.price_tracker.core.repository.ProductRepository;
import ru.nugumanov.price_tracker.jooq.tables.pojos.ProductPojo;
import ru.nugumanov.price_tracker.jooq.tables.records.ProductRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.nugumanov.price_tracker.jooq.Tables.PRODUCT;

/**
 * Реализация репозитория для работы с продуктами
 */
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final DSLContext dsl;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductPojo> get() {
        return dsl.selectFrom(PRODUCT)
                .fetchInto(ProductPojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProductPojo> get(UUID id) {
        return dsl.selectFrom(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .fetchOptionalInto(ProductPojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(ProductRecord record) {
        dsl.insertInto(PRODUCT)
                .set(record)
                .onConflictDoNothing()
                .execute();
    }
}
