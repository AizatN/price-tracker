package ru.nugumanov.price_tracker.core.service;

import ru.nugumanov.price_common.model.ParseResultModel;
import ru.nugumanov.price_common.model.ProductOffersModel;

import java.util.List;

/**
 * Сервис для работы с вариантами продажи продукта
 */
public interface ProductOffersService {

    /**
     * Получение всех моделей варианта продажи продукта
     *
     * @return Список моделей варианта продажи продукта
     */
    List<ProductOffersModel> get();

    /**
     * Обработка результата парсинга
     *
     * @param parseResultModel Модель результата парсинга
     */
    void processParseResult(ParseResultModel parseResultModel);
}
