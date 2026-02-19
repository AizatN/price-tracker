package ru.nugumanov.price_tracker.notifier.feign;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import ru.nugumanov.price_common.model.PriceReportModel;
import ru.nugumanov.price_tracker.notifier.feign.config.FeignJaxrsConfig;

import java.util.List;

@FeignClient(
        name = "coreClient",
        url = "${core.url}",
        configuration = FeignJaxrsConfig.class
)
@Path("/price-report")
public interface PriceReportRestFeign {

    @GET
    List<PriceReportModel> get();
}
