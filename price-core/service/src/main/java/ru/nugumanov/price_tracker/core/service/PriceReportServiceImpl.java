package ru.nugumanov.price_tracker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_common.model.PriceReportModel;
import ru.nugumanov.price_tracker.core.repository.PriceReportRepository;

import java.util.List;

/**
 * Реализация сервиса для получения отчета о ценах
 */
@Service
@RequiredArgsConstructor
public class PriceReportServiceImpl implements PriceReportService {

    private final PriceReportRepository priceReportRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PriceReportModel> get() {
        return priceReportRepository.get();
    }
}
