package ru.nugumanov.price_tracker.collector.strategy;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import ru.nugumanov.price_tracker.collector.properties.SelectorProperties.Selector;

import java.io.IOException;

@Slf4j
@Component
public class HtmlParserStrategy implements ParserStrategy {

    private static final String TYPE = "html";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void parse(Selector selector) {
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
