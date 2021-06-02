package com.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.microservices.currencyexchangeservice.repositry.CurrencyExchangeRepositry;

@RestController
public class CurrencyExchangeController {

	@Value("${server.port}")
	String port;

	@Autowired
	CurrencyExchangeRepositry repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange obj = repository.findByFromAndTo(from, to);
		if (obj == null) {
			throw new RuntimeException("unable to find data for parms");
		}
		obj.setEnvironment(port);
		return obj;

	}

}