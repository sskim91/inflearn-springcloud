package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sskim on 2022/05/21
 */
@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {

    @GetMapping("/welcome")
    public String hello() {
        return "welcome second service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info("header = {}", header);
        return "Hello World API Second Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from Second Service.";
    }
}
