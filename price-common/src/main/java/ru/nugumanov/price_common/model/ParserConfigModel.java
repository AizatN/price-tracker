package ru.nugumanov.price_common.model;


import lombok.*;

/**
 * Модель конфигурации парсинга
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParserConfigModel {

    private String title;
    private String price;
}
