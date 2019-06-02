package com.udemy.microservices.currencyconversionservice.controllers;

import com.udemy.microservices.currencyconversionservice.domain.CurrencyConversionBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("from", from);
        uriParams.put("to", to);
        ResponseEntity<CurrencyConversionBean> response = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriParams
        );

        CurrencyConversionBean result = response.getBody();

        if(result == null) {
            return CurrencyConversionBean.builder().build();
        }

        return CurrencyConversionBean.builder()
                .id(1L).from(from).to(to)
                .quantity(quantity)
                .conversionMultiple(result.getConversionMultiple())
                .totalCalculatedAmount(quantity.multiply(result.getConversionMultiple()))
                .build();
    }
}
