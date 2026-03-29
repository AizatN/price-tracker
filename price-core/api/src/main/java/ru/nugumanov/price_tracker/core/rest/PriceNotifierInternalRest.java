package ru.nugumanov.price_tracker.core.rest;

import org.springframework.web.bind.annotation.*;
import ru.nugumanov.price_common.model.PriceReportModel;
import ru.nugumanov.price_common.model.TelegramUserModel;

import java.util.List;

/**
 * Рест сервис для получения отчета о ценах
 */
public interface PriceNotifierInternalRest {

    /**
     * Получение отчета о текущих ценах
     *
     * @return Список моделей отчета о ценах
     */
    @GetMapping("/price-report")
    List<PriceReportModel> getPriceReport();

    /**
     * Получение всех активных пользователей бота
     *
     * @return Список активных пользователей телеграм бота
     */
    @GetMapping("/active")
    List<TelegramUserModel> getActive();

    /**
     * Создание пользователя телеграм бота
     *
     * @param model Пользователь телеграм бота
     */
    @PostMapping
    void save(@RequestBody TelegramUserModel model);

    /**
     * Удаление пользователя телеграм бота
     *
     * @param chatId Чат ID пользователя
     */
    @DeleteMapping("/{chatId}")
    void delete(@PathVariable("chatId") String chatId);
}
