package ru.nugumanov.price_tracker.core.rest;

import org.springframework.web.bind.annotation.GetMapping;
import ru.nugumanov.price_common.model.SelectorModel;

import java.util.List;

/**
 * Рест сервис для работы моделями выбора значений
 */
public interface SelectorRest {

    /**
     * Получение всех моделей выбора значений
     *
     * @return Список моделей выбора значений
     */
    @GetMapping
    List<SelectorModel> get();
}
