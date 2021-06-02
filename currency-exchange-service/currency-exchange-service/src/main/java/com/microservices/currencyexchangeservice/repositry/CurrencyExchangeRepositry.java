package com.microservices.currencyexchangeservice.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.currencyexchangeservice.entity.CurrencyExchange;

public interface CurrencyExchangeRepositry extends JpaRepository<CurrencyExchange, Long> {

	public CurrencyExchange findByFromAndTo(String from, String to);

}
