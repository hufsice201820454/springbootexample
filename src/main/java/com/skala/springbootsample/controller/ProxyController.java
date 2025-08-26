package com.skala.springbootsample.controller;

import com.skala.springbootsample.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private final MyService myService;

    public ProxyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/api/proxy")
    public String callProxyService() {
        return myService.getMessage("proxy-test");
    }
}

