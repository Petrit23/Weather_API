package com.petritkrasniqi.weatherapi.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;

public class BadRequestExceptionTest extends WeatherAPIJunitTest {

	@Test
	public void customMessageSuppliedInConstructor_exceptionPopulatedWithDetailedErrorMessage() {

		assertEquals(EXCEPTION_MSG, ex.getMessage());
	}

}
