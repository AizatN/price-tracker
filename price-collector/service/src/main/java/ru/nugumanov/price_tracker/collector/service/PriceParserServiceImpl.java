package ru.nugumanov.price_tracker.collector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import ru.nugumanov.price_tracker.collector.properties.SelectorProperties;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceParserServiceImpl implements PriceParserService {

    private final SelectorProperties properties;

    @Override
    public void parse() {
        for (var selector : properties.getSelectors()) {
            try {
                var doc = Jsoup.connect(selector.getUrl())
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .get();

                var title = doc.selectFirst(selector.getTitle());
                var priceEl = doc.selectFirst(selector.getPrice());

                assert title != null;
                assert priceEl != null;
                System.out.println(title.text() + " - " + priceEl.text());
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
