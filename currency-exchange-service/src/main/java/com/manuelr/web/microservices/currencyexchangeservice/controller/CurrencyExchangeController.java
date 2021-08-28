package com.manuelr.web.microservices.currencyexchangeservice.controller;

import com.manuelr.web.microservices.currencyexchangeservice.model.CurrencyExchange;

import com.manuelr.web.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CurrencyExchangeController {
    private static final String NOT_FOUND_MSG = "Unable to find data";

    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from,
                                                  @PathVariable("to") String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException(NOT_FOUND_MSG);
        }

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        return currencyExchange;
    }
}
