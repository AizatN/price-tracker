package ru.nugumanov.price_tracker.core.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nugumanov.price_common.model.SelectorModel;
import ru.nugumanov.price_tracker.core.mapper.SelectorModelMapper;
import ru.nugumanov.price_tracker.core.service.ProductOffersService;

import java.util.List;

/**
 * Реализация реста для работы с моделями выбора значений
 */
@RestController
@RequestMapping("/selector")
@RequiredArgsConstructor
public class SelectorRestImpl implements SelectorRest {

    private final ProductOffersService productOffersService;

    /**
     * {@inheritDoc}
     */
    @GetMapping
    @Override
    public List<SelectorModel> get() {
        return SelectorModelMapper.get(productOffersService.get());
    }
}
