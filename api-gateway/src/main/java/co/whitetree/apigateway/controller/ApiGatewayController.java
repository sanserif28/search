package co.whitetree.apigateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiGatewayController {

    @GetMapping("/")
    public String index() {
        log.debug("Gateway 서버입니다.");
        return "Gateway 서버입니다.";
    }
}
