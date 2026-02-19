package ru.nugumanov.price_tracker.notifier.feign.config;

import feign.Contract;
import feign.jaxrs3.JAXRS3Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignJaxrsConfig {

    @Bean
    public Contract feignContract() {
        return new JAXRS3Contract();
    }
}
