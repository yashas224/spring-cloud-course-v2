package com.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.entity.CurrencyExchange;
import com.microservices.currencyexchangeservice.repositry.CurrencyExchangeRepositry;

@RestController
public class CurrencyExchangeController {
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
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
		logger.info("CurrencyExchangeController called with parameters {} {}", from, to);
		return obj;

	}

}