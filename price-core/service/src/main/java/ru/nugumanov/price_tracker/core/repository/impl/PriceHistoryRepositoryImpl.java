package ru.nugumanov.price_tracker.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.nugumanov.price_tracker.core.repository.PriceHistoryRepository;
import ru.nugumanov.price_tracker.jooq.tables.records.PriceHistoryRecord;

import static ru.nugumanov.price_tracker.jooq.Tables.PRICE_HISTORY;

/**
 * Реализация репозитория для работы с историей цен
 */
@Repository
@RequiredArgsConstructor
public class PriceHistoryRepositoryImpl implements PriceHistoryRepository {

    private final DSLContext dsl;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PriceHistoryRecord record) {
        dsl.insertInto(PRICE_HISTORY)
                .set(record)
                .onConflictDoNothing()
                .execute();
    }
}
