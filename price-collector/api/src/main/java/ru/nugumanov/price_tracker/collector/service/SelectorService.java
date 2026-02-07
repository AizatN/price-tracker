package ru.nugumanov.price_tracker.collector.service;

import ru.nugumanov.price_common.model.SelectorModel;

import java.util.List;

/**
 * Сервис для работы с моделями выбора значений
 */
public interface SelectorService {

    /**
     * Получение списка моделей выбора значений
     *
     * @return Список моделей выбора значений
     */
    List<SelectorModel> get();
}
