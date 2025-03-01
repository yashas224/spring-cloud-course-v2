package com.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limitsservice.config.Configuration;
import com.microservices.limitsservice.dto.Limits;

@RestController
public class LimitsController {

	@Autowired
	Configuration config;

	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(config.getMinimum(), config.getMaximum());
	}
}