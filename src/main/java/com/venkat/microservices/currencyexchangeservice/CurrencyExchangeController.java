package com.venkat.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRepo currencyExchangeRepo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeRate getExchangeRate(@PathVariable String from, @PathVariable String to){

       ExchangeRate exchangeRate = currencyExchangeRepo.findByFromAndTo(from,to);
        if(exchangeRate ==null) {
            throw new RuntimeException
                    ("Unable to Find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        exchangeRate.setEnvironment(port);
        return exchangeRate;
    }

}
