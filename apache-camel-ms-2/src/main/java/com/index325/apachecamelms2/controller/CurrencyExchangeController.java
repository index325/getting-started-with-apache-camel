package com.index325.apachecamelms2.controller;

import com.index325.apachecamelms2.dto.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequestMapping("/currency-exchange")
@RestController
public class CurrencyExchangeController {

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange findConversionValue(@PathVariable String from,
                                                @PathVariable String to) {

        return new CurrencyExchange(1001L, from, to, BigDecimal.TEN);

    }


}
