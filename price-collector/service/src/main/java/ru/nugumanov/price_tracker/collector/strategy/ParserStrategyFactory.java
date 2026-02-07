package ru.nugumanov.price_tracker.collector.strategy;

import org.springframework.stereotype.Component;
import ru.nugumanov.price_common.enums.ParserTypeEnum;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ParserStrategyFactory {

    private final Map<ParserTypeEnum, ParserStrategy> strategies;

    public ParserStrategyFactory(List<ParserStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ParserStrategy::getType, s -> s));
    }

    public ParserStrategy getStrategy(ParserTypeEnum type) {
        var strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown parser type: " + type);
        }
        return strategy;
    }
}
