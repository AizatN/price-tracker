package ru.nugumanov.price_common.model;

import lombok.*;
import ru.nugumanov.price_common.enums.ParserTypeEnum;

import java.util.UUID;

/**
 * Модель выбора значений
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectorModel {

    private Long productOfferId;
    private String url;
    private String title;
    private String price;
    private ParserTypeEnum type;
}
