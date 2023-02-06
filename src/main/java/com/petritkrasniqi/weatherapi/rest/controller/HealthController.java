package com.petritkrasniqi.weatherapi.rest.controller;

import static org.springframework.http.HttpStatus.OK;
import static com.petritkrasniqi.weatherapi.util.LoggerUtil.logInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@GetMapping("/health")
	public ResponseEntity<String> getHealthStatus() {
		logInfo("Successful request to health endpoint");
		
		return ResponseEntity.status(OK).body("Successful connection to WeatherAPI app");
	}

}
