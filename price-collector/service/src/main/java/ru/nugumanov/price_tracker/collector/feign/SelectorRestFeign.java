package ru.nugumanov.price_tracker.collector.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.nugumanov.price_tracker.core.rest.SelectorRest;

@FeignClient(
        name = "coreClient",
        url = "${core.url}",
        path = "/selector"
)
public interface SelectorRestFeign extends SelectorRest {
}
