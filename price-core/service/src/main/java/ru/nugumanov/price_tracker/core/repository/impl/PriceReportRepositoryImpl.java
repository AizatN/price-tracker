package ru.nugumanov.price_tracker.core.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.nugumanov.price_common.model.PriceReportModel;
import ru.nugumanov.price_tracker.core.repository.PriceReportRepository;

import java.util.List;

import static org.jooq.impl.DSL.max;
import static ru.nugumanov.price_tracker.jooq.Tables.*;

/**
 * Реализация репозитория для получения отчета о ценах
 */
@Repository
@RequiredArgsConstructor
public class PriceReportRepositoryImpl implements PriceReportRepository {

    private final DSLContext dsl;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PriceReportModel> get() {
        var latestPrices = dsl
                .select(
                        PRICE_HISTORY.PRODUCT_OFFERS_ID,
                        max(PRICE_HISTORY.PARSED_DT).as("max_parsed_dt")
                )
                .from(PRICE_HISTORY)
                .groupBy(PRICE_HISTORY.PRODUCT_OFFERS_ID)
                .asTable("lp");

        var lpProductOffersId = latestPrices.field(PRICE_HISTORY.PRODUCT_OFFERS_ID);
        var lpMaxParsedDt = latestPrices.field("max_parsed_dt", PRICE_HISTORY.PARSED_DT.getDataType());

        return dsl
                .select(
                        PRODUCT_OFFERS.ID,
                        PRODUCTS.NAME,
                        SOURCES.NAME,
                        PRODUCT_OFFERS.URL,
                        PRICE_HISTORY.PRICE,
                        PRICE_HISTORY.PARSED_DT
                )
                .from(PRODUCT_OFFERS)
                .join(PRODUCTS).on(PRODUCT_OFFERS.PRODUCT_ID.eq(PRODUCTS.ID))
                .join(SOURCES).on(PRODUCT_OFFERS.SOURCE_ID.eq(SOURCES.ID))
                .join(latestPrices).on(PRODUCT_OFFERS.ID.eq(lpProductOffersId))
                .join(PRICE_HISTORY).on(
                        PRICE_HISTORY.PRODUCT_OFFERS_ID.eq(PRODUCT_OFFERS.ID)
                                .and(PRICE_HISTORY.PARSED_DT.eq(lpMaxParsedDt))
                )
                .fetch(r -> PriceReportModel.builder()
                        .productOfferId(r.get(PRODUCT_OFFERS.ID))
                        .productName(r.get(PRODUCTS.NAME))
                        .sourceName(r.get(SOURCES.NAME))
                        .url(r.get(PRODUCT_OFFERS.URL))
                        .currentPrice(r.get(PRICE_HISTORY.PRICE))
                        .lastUpdated(r.get(PRICE_HISTORY.PARSED_DT))
                        .build()
                );
    }
}
