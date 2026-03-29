package ru.nugumanov.price_tracker.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.nugumanov.price_tracker.core.repository.TelegramUserRepository;
import ru.nugumanov.price_tracker.jooq.tables.pojos.TelegramUserPojo;
import ru.nugumanov.price_tracker.jooq.tables.records.TelegramUserRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static ru.nugumanov.price_tracker.jooq.Tables.TELEGRAM_USER;

/**
 * Реализация репозитория для работы с пользователями телеграм бота
 */
@Repository
@RequiredArgsConstructor
public class TelegramUserRepositoryImpl implements TelegramUserRepository {

    private final DSLContext dsl;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TelegramUserPojo> get(String chatId) {
        return dsl.selectFrom(TELEGRAM_USER)
                .where(TELEGRAM_USER.CHAT_ID.eq(chatId))
                .fetchOptionalInto(TelegramUserPojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TelegramUserPojo> getActive() {
        return dsl.selectFrom(TELEGRAM_USER)
                .where(TELEGRAM_USER.DELETED_DT.isNull())
                .fetchInto(TelegramUserPojo.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TelegramUserRecord record) {
        dsl.insertInto(TELEGRAM_USER)
                .set(record)
                .onConflict(TELEGRAM_USER.CHAT_ID)
                .doUpdate()
                .set(TELEGRAM_USER.DELETED_DT, (LocalDateTime) null)
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String chatId) {
        dsl.update(TELEGRAM_USER)
                .set(TELEGRAM_USER.DELETED_DT, now())
                .where(TELEGRAM_USER.CHAT_ID.eq(chatId))
                .execute();

    }
}
