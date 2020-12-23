package test.example.alpha.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final BoxService boxService;

    public RestController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping("/test")
    public List<Long> getItemsByIdAndColor(@RequestBody BoxRequest requestBody) {
        log.debug("Request for REST getItemsByIdAndColor");
        return boxService.getItems(requestBody);
    }
}
