package ru.nugumanov.price_tracker.collector.strategy;

import ru.nugumanov.price_tracker.collector.properties.SelectorProperties.Selector;

public interface ParserStrategy {

    String getType();

    void parse(Selector selector);
}
