package ru.nugumanov.price_common.model;

import lombok.*;

/**
 * Модель результата парсинга
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParseResultModel {

    private Long productOfferId;
    private String price;
}
