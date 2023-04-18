package com.example.microservice.limitsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.microservices.limitsservice.bean.LimitConfiguration;

import jakarta.websocket.server.PathParam;

@RestController
public class LimitsConfigurationController {
/*	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFromConfigurations() {
		return new LimitConfiguration(1000, 1);
	}*/
	@GetMapping("/hello/{name}")
	public String getName(@PathVariable String name) {
		return "Hello "+name;
	}
	
	@GetMapping("/helloqueryparam")
	public String getNameQuery(@RequestParam String name, @RequestParam(required=false) String country ) {
		return "Hello "+name;
	}
	
}