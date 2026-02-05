package ru.nugumanov.price_common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Тип парсинга
 */
@Getter
@RequiredArgsConstructor
public enum ParserTypeEnum {
    HTML,
    JS
}
