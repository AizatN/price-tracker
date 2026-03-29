package ru.nugumanov.price_tracker.notifier.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.nugumanov.price_tracker.core.rest.PriceNotifierInternalRest;

@FeignClient(
        name = "coreClient",
        url = "${core.url}",
        path = "/internal/notifier"
)
public interface PriceReportRestFeign extends PriceNotifierInternalRest {
}
