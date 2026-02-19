package ru.nugumanov.price_tracker.core.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nugumanov.price_common.model.PriceReportModel;
import ru.nugumanov.price_tracker.core.service.PriceReportService;

import java.util.List;

/**
 * Реализация реста для получения отчета о ценах
 */
@RestController
@RequestMapping("/price-report")
@RequiredArgsConstructor
public class PriceReportRestImpl implements PriceReportRest {

    private final PriceReportService priceReportService;

    /**
     * {@inheritDoc}
     */
    @GetMapping
    @Override
    public List<PriceReportModel> get() {
        return priceReportService.get();
    }
}
