package ru.nugumanov.price_tracker.core.service;

import ru.nugumanov.price_common.model.PriceReportModel;

import java.util.List;

/**
 * Сервис для получения отчета о ценах
 */
public interface PriceReportService {

    /**
     * Получение отчета о текущих ценах
     *
     * @return Список моделей отчета о ценах
     */
    List<PriceReportModel> get();
}
