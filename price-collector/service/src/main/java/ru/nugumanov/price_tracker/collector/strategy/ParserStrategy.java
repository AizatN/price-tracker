package ru.nugumanov.price_tracker.collector.strategy;

import ru.nugumanov.price_common.enums.ParserTypeEnum;
import ru.nugumanov.price_common.model.ParseResultModel;
import ru.nugumanov.price_common.model.SelectorModel;

public interface ParserStrategy {

    ParserTypeEnum getType();

    ParseResultModel parse(SelectorModel selector);
}
