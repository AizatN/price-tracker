package ru.nugumanov.price_tracker.core.repository;

import ru.nugumanov.price_common.model.PriceReportModel;

import java.util.List;

/**
 * Репозиторий для получения отчета о ценах
 */
public interface PriceReportRepository {

    /**
     * Получение отчета о текущих ценах
     *
     * @return Список моделей отчета о ценах
     */
    List<PriceReportModel> get();
}
