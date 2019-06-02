package com.udemy.microservices.currencyconversionservice.controllers;

import com.udemy.microservices.currencyconversionservice.domain.CurrencyConversionBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {
        return CurrencyConversionBean.builder()
                .id(1L).from(from).to(to)
                .quantity(quantity)
                .conversionMultiple(BigDecimal.ONE)
                .totalCalculatedAmount(quantity)
                .build();
    }
}
