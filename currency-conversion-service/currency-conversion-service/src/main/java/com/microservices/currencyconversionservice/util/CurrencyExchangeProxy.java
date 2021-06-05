package com.microservices.currencyconversionservice.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.currencyconversionservice.dto.CurrencyConversion;

// if a service discovery is not happening 
//@FeignClient(name = "currency-exchange", url = "http://localhost:8000")
// if using a service discovery
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable(name = "from") String from,
			@PathVariable(name = "to") String to);
}
