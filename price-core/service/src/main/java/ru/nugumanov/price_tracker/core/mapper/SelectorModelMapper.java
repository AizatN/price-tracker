package ru.nugumanov.price_tracker.core.mapper;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import ru.nugumanov.price_common.model.ProductOffersModel;
import ru.nugumanov.price_common.model.SelectorModel;

import java.util.List;

import static java.util.Objects.isNull;

@UtilityClass
public class SelectorModelMapper {

    /**
     * Получение модели выбора значений
     *
     * @param productOffers Модель варианта продажи продукта
     * @return Модель выбора значений
     */
    public SelectorModel get(ProductOffersModel productOffers) {
        if (isNull(productOffers)) {
            return null;
        }
        var parseConfig = productOffers.getParserConfig();
        return SelectorModel.builder()
                .url(productOffers.getUrl())
                .title(parseConfig == null ? null : parseConfig.getTitle())
                .price(parseConfig == null ? null : parseConfig.getPrice())
                .type(productOffers.getParseType())
                .build();
    }

    /**
     * Получение списка моделей выбора значений
     *
     * @param productOffersModels Список вариантов продажи продукта
     * @return Список моделей выбора значений
     */
    public List<SelectorModel> get(List<ProductOffersModel> productOffersModels) {
        if (CollectionUtils.isEmpty(productOffersModels)) {
            return List.of();
        }
        return productOffersModels.stream().map(SelectorModelMapper::get).toList();
    }
}
