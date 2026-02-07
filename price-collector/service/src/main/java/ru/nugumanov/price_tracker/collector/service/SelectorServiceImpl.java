package ru.nugumanov.price_tracker.collector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_common.model.SelectorModel;
import ru.nugumanov.price_tracker.collector.feign.SelectorRestFeign;

import java.util.List;

/**
 * Реализация сервиса для работы с моделями выбора значений
 */
@Service
@RequiredArgsConstructor
public class SelectorServiceImpl implements SelectorService {

    private final SelectorRestFeign selectorRestFeign;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SelectorModel> get() {
        return selectorRestFeign.get();
    }
}
