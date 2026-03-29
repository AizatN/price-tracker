package ru.nugumanov.price_tracker.core.mapper;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ru.nugumanov.price_common.model.TelegramUserModel;
import ru.nugumanov.price_tracker.jooq.tables.pojos.TelegramUserPojo;
import ru.nugumanov.price_tracker.jooq.tables.records.TelegramUserRecord;

import java.util.List;

import static java.util.Objects.isNull;

@UtilityClass
public class TelegramUserMapper {

    /**
     * Получение модели пользователя телеграм бота из записи
     *
     * @param pojo Пользователь телеграм бота
     * @return Модель пользователя телеграм бота
     */
    public TelegramUserModel get(TelegramUserPojo pojo) {
        if (isNull(pojo)) {
            return null;
        }
        return TelegramUserModel.builder()
                .chatId(pojo.getChatId())
                .createdDt(pojo.getCreatedDt())
                .deletedDt(pojo.getDeletedDt())
                .build();
    }

    /**
     * Получение списка моделей пользователя телеграм бота из списка записей
     *
     * @param pojos Список пользователей телеграм бота
     * @return Список моделей пользователей телеграм бота
     */
    public List<TelegramUserModel> getModels(List<TelegramUserPojo> pojos) {
        if (CollectionUtils.isEmpty(pojos)) {
            return List.of();
        }
        return pojos.stream().map(TelegramUserMapper::get).toList();
    }

    /**
     * Получение записи пользователя телеграм бота из модели
     *
     * @param model Модель пользователя телеграм бота
     * @return Запись пользователя телеграм бота
     */
    public TelegramUserRecord get(TelegramUserModel model) {
        if (isNull(model)) {
            return null;
        }
        var record = new TelegramUserRecord();
        record.setChatId(model.getChatId());
        record.setCreatedDt(model.getCreatedDt());
        record.setDeletedDt(model.getDeletedDt());
        return record;
    }

    /**
     * Получение списка записей пользователей телеграм бота из списка моделей
     *
     * @param models Список моделей пользователей телеграм бота
     * @return Список записей пользователей телеграм бота
     */
    public List<TelegramUserRecord> getRecords(List<TelegramUserModel> models) {
        if (CollectionUtils.isEmpty(models)) {
            return List.of();
        }
        return models.stream().map(TelegramUserMapper::get).toList();
    }
}
