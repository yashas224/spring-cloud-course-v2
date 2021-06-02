package com.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.currencyconversionservice.dto.CurrencyConversion;
import com.microservices.currencyconversionservice.util.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

		CurrencyConversion currencyConversion = responseEntity.getBody();
		currencyConversion.setTotalCalculatedAmount((quantity.multiply(currencyConversion.getConversionMultiple())));
		currencyConversion.setEnvironment(currencyConversion.getEnvironment() + "--rest template");
		return currencyConversion;
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFegn(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
		currencyConversion.setTotalCalculatedAmount((quantity.multiply(currencyConversion.getConversionMultiple())));
		currencyConversion.setEnvironment(currencyConversion.getEnvironment() + "--Feign Call");
		return currencyConversion;
	}

}