package ru.nugumanov.price_tracker.core.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.experimental.UtilityClass;
import ru.nugumanov.price_common.model.ParserConfigModel;
import ru.nugumanov.price_common.model.ProductOffersModel;
import ru.nugumanov.price_tracker.jooq.tables.pojos.ProductOffersPojo;

import java.util.List;

@UtilityClass
public class ProductOffersMapper {

    /**
     * Получение модели варианта продажи продукта из записи
     *
     * @param pojo Вариант продажи продукта
     * @return Модель варианта продажи продукта
     */
    public ProductOffersModel get(ProductOffersPojo pojo) {
        ParserConfigModel parseConfig;
        try {
            parseConfig = ParserConfigMapper.get(pojo.getParserConfig());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ProductOffersModel.builder()
                .id(pojo.getId())
                .sourceId(pojo.getSourceId())
                .productId(pojo.getProductId())
                .url(pojo.getUrl())
                .parseType(pojo.getParseType())
                .parserConfig(parseConfig)
                .lastParseAttempt(pojo.getLastParseAttempt())
                .lastSuccessParse(pojo.getLastSuccessParse())
                .createDt(pojo.getCreateDt())
                .updatedAt(pojo.getUpdatedAt())
                .build();
    }

    /**
     * Получение списка моделей варианта продажи продукта из записей
     *
     * @param pojos Список записей варианта продажи продуктов
     * @return Список моделей варианта продажи продукта
     */
    public List<ProductOffersModel> get(List<ProductOffersPojo> pojos) {
        return pojos.stream().map(ProductOffersMapper::get).toList();
    }
}
