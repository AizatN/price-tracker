package ru.nugumanov.price_tracker.collector.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "price-collector")
public class SelectorProperties {
    private List<Selector> selectors;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Selector {
        private String url;
        private String title;
        private String price;
    }
}
