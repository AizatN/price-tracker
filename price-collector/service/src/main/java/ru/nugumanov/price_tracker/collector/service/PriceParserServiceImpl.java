package ru.nugumanov.price_tracker.collector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_tracker.collector.strategy.ParserStrategyFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceParserServiceImpl implements PriceParserService {

    private final SelectorService selectorService;
    private final ParserStrategyFactory parserStrategyFactory;

    @Override
    public void parse() {
        var selectors = selectorService.get();
        for (var selector : selectors) {
            var strategy = parserStrategyFactory.getStrategy(selector.getType());
            strategy.parse(selector);
        }
    }
}
