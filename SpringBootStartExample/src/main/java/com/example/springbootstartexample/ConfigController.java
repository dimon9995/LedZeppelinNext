package com.example.springbootstartexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${server.app.title}")
    private String appTitle;
    @Value("${server.app.description}")
    private String appDescription;
    @Value("${server.app.author.name}")
    private String authorName;

    @GetMapping("/config")
    public String getAppTitle() {
        return String.format("%s - %s (автор: %s)", appTitle, appDescription, authorName);
    }

}
