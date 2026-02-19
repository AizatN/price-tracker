package ru.nugumanov.price_tracker.core.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import ru.nugumanov.price_common.model.PriceReportModel;

import java.util.List;

/**
 * Рест сервис для получения отчета о ценах
 */
@Path("/price-report")
public interface PriceReportRest {

    /**
     * Получение отчета о текущих ценах
     *
     * @return Список моделей отчета о ценах
     */
    @GET
    List<PriceReportModel> get();
}
