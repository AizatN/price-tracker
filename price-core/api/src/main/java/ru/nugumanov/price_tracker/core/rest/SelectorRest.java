package ru.nugumanov.price_tracker.core.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import ru.nugumanov.price_common.model.SelectorModel;

import java.util.List;

/**
 * Рест сервис для работы моделями выбора значений
 */
@Path("/selector")
public interface SelectorRest {

    /**
     * Получение всех моделей выбора значений
     *
     * @return Список моделей выбора значений
     */
    @GET
    List<SelectorModel> get();
}
