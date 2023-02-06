package com.petritkrasniqi.weatherapi.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;

public class BadRequestExceptionHandlerTest extends WeatherAPIJunitTest {

	private BadRequestExceptionHandler badRequestExceptionHandler = new BadRequestExceptionHandler();

	@Test
	public void badRequestExceptionCaught_400StatusCodeReturnedWithErrorMessageFromException() {

		ResponseEntity<String> response = badRequestExceptionHandler.handleBadRequestException(ex);

		assertEquals(400, response.getStatusCodeValue());
		assertEquals(EXCEPTION_MSG, response.getBody());
	}

}
