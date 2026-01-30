package io.javabrains.spring_boot_config.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${my.greetings}")
    private String greetingMsg;

    @Value("${app.description}")
    private String appDesc;

    @GetMapping("/greeting")
    public String getGreeting() {
        return greetingMsg;
    }

    @GetMapping("/appDesc")
    public String getAppDesc(){
        return appDesc;
    }
}
