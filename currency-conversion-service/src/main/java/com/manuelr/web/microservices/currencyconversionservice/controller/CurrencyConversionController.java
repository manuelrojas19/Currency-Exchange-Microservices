package com.manuelr.web.microservices.currencyconversionservice.controller;

import com.manuelr.web.microservices.currencyconversionservice.model.CurrencyConversion;
import com.manuelr.web.microservices.currencyconversionservice.web.CurrencyExchangeProxy;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {
    private static final String NOT_FOUND_MSG = "Unable to find";
//    private static final String CURRENCY_CONVERSION_URL = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

    private final CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {

//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("from", from);
//        uriVariables.put("to", to);
//
//        ResponseEntity<CurrencyConversion> currencyConversionResponseEntity = new RestTemplate()
//                .getForEntity(CURRENCY_CONVERSION_URL,
//                        CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);

        if (currencyConversion == null) {
            throw new RuntimeException(NOT_FOUND_MSG);
        }

        return CurrencyConversion.builder().id(currencyConversion.getId())
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .environment(currencyConversion.getEnvironment())
                .build();
    }
}
