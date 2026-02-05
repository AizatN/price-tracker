package ru.nugumanov.price_common.model;

import lombok.*;
import ru.nugumanov.price_common.enums.ParserTypeEnum;

import java.time.LocalDateTime;

/**
 * Модель варианта продажи продукта
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOffersModel {

    private Long id;
    private Long sourceId;
    private Long productId;
    private String url;
    private ParserTypeEnum parseType;
    private ParserConfigModel parserConfig;
    private LocalDateTime lastParseAttempt;
    private LocalDateTime lastSuccessParse;
    private LocalDateTime createDt;
    private LocalDateTime updatedAt;
}
