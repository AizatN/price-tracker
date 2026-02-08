package ru.nugumanov.price_tracker.collector.feign;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import ru.nugumanov.price_common.model.SelectorModel;
import ru.nugumanov.price_tracker.collector.feign.config.FeignJaxrsConfig;

import java.util.List;

@FeignClient(
        name = "coreClient",
        url = "${core.url}",
        configuration = FeignJaxrsConfig.class
)
@Path("/selector")
public interface SelectorRestFeign {

    @GET
    List<SelectorModel> get();
}
