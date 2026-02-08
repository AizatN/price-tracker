package ru.nugumanov.price_tracker.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_common.model.ParseResultModel;
import ru.nugumanov.price_common.model.ProductOffersModel;
import ru.nugumanov.price_tracker.core.mapper.ProductOffersMapper;
import ru.nugumanov.price_tracker.core.repository.PriceHistoryRepository;
import ru.nugumanov.price_tracker.core.repository.ProductOffersRepository;
import ru.nugumanov.price_tracker.jooq.tables.records.PriceHistoryRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация сервиса для работы с вариантами продажи продукта
 */
@Service
@RequiredArgsConstructor
public class ProductOffersServiceImpl implements ProductOffersService {

    private final ProductOffersRepository productOffersRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductOffersModel> get() {
        return ProductOffersMapper.get(productOffersRepository.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processParseResult(ParseResultModel parseResultModel) {
        var productOfferOptional = productOffersRepository.get(parseResultModel.getProductOfferId());
        if (productOfferOptional.isEmpty()) {
            return;
        }
        var productOffer = productOfferOptional.get();
        var priceHistoryRecord = new PriceHistoryRecord();
        priceHistoryRecord.setProductOffersId(productOffer.getId());
        priceHistoryRecord.setParsedDt(LocalDateTime.now());
        priceHistoryRecord.setAvailability(Boolean.TRUE);
        var priceString = parseResultModel.getPrice().replaceAll("[^\\d.]", "");
        var price = new BigDecimal(priceString);
        priceHistoryRecord.setPrice(price);
        priceHistoryRepository.save(priceHistoryRecord);
    }
}
