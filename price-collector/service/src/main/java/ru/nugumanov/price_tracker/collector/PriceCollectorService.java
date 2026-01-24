package ru.nugumanov.price_tracker.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.nugumanov.price_tracker.collector.properties.SelectorProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({SelectorProperties.class})
public class PriceCollectorService {
    public static void main(String[] args) {
        SpringApplication.run(PriceCollectorService.class, args);
    }
}
