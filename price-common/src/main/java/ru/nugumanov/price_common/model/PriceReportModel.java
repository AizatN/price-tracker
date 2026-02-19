package ru.nugumanov.price_common.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Модель отчета о ценах
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceReportModel {

    private Long productOfferId;
    private String productName;
    private String sourceName;
    private String url;
    private BigDecimal currentPrice;
    private LocalDateTime lastUpdated;
}
