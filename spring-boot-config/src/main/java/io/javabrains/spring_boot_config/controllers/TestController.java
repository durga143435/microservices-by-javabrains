package io.javabrains.spring_boot_config.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${credentials.username}")
    private String username;

    @Value("${credentials.password}")
    private String password;

    @GetMapping("/credentials")
    public String getCredentials(){
        return "username: "+ username +" & password: " +password;
    }
}
