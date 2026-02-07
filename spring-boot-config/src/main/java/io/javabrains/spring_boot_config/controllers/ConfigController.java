package io.javabrains.spring_boot_config.controllers;

import io.javabrains.spring_boot_config.config.DbSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${my.greetings: app.desc}")
    private String greetingMsg;

    @Value("${my.list.values}")
    private List<String> values;

    @Value("#{${dbValues}}")
    private Map<String, String> keyValues;

    @Value("${app.description}")
    private String appDesc;

    @Value("${custom.value}")
    private String appCustValue;

    @Autowired
    private DbSettings dbSettings;

    @GetMapping("/greeting")
    public String getGreeting() {
        return greetingMsg + values + keyValues + appCustValue;
    }

    @GetMapping("/appDesc")
    public String getAppDesc(){
        return appDesc;
    }

    @GetMapping("/dbSettings")
    public String dbSettings(){
        return dbSettings.getConnection()+ dbSettings.getHost();
    }
}
