package com.venkat.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepo extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findByFromAndTo(String from,String to);
}
