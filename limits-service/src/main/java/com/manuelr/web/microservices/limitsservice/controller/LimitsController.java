package com.manuelr.web.microservices.limitsservice.controller;

import com.manuelr.web.microservices.limitsservice.bean.Limits;
import com.manuelr.web.microservices.limitsservice.config.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LimitsController {

    private final Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
