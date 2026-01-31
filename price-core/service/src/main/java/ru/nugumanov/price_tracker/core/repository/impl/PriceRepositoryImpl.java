package ru.nugumanov.price_tracker.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.nugumanov.price_tracker.core.repository.PriceRepository;
import ru.nugumanov.price_tracker.jooq.tables.pojos.PricePojo;
import ru.nugumanov.price_tracker.jooq.tables.records.PriceRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.nugumanov.price_tracker.jooq.Tables.PRICE;

/**
 * Реализация репозитория для работы с ценами
 */
@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final DSLContext dsl;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PricePojo> get() {
        return dsl.selectFrom(PRICE)
                .fetchInto(PricePojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PricePojo> get(UUID id) {
        return dsl.selectFrom(PRICE)
                .where(PRICE.ID.eq(id))
                .fetchOptionalInto(PricePojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PriceRecord record) {
        dsl.insertInto(PRICE)
                .set(record)
                .onConflictDoNothing()
                .execute();
    }
}
