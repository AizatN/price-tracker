package ru.nugumanov.price_tracker.collector.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import ru.nugumanov.price_tracker.collector.properties.SelectorProperties.Selector;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@Component
public class JsParserStrategy implements ParserStrategy {

    private static final String TYPE = "js";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void parse(Selector selector) {
        Document doc;
        try {
            doc = Jsoup.connect(selector.getUrl())
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var mapper = new ObjectMapper();

        var scripts = doc.select("script[type=application/ld+json]");

        JsonNode productJson = null;

        for (var script : scripts) {
            String json = script.html();

            JsonNode root;
            try {
                root = mapper.readTree(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            String type = root.path("@type").asText();

            if ("Product".equalsIgnoreCase(type) || "ProductGroup".equalsIgnoreCase(type)) {
                productJson = root;
                break;
            }
        }

        if (productJson == null) {
            throw new RuntimeException("Product JSON-LD не найден");
        }

        for (JsonNode variant : productJson.path("hasVariant")) {
            String name = variant.path("name").asText().toLowerCase();

            if (name.equalsIgnoreCase(selector.getTitle())) {
                var price = new BigDecimal(
                        variant.path("offers").path("price").asText()
                );

                System.out.println(selector.getTitle() + " - " + price);
                break;
            }
        }
    }
}
