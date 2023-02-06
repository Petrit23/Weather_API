package com.petritkrasniqi.weatherapi.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;
import com.petritkrasniqi.weatherapi.entity.Weather;

public class WeatherDataTest extends WeatherAPIJunitTest {
	
	@Test
	public void toEntity() {
		assertEntity(weather.toEntity());
	}
	
	@Test
	public void test_toString() {
		assertEquals(WEATHER_TO_STRING, weather.toString());
	}

	private void assertEntity(Weather entity) {
		assertEquals(ID, entity.getId());
		assertEquals(CREATED_DATE, entity.getCreatedDate());
		assertEquals(TEMPERATURE, entity.getTemperature());
		assertEquals(HUMIDITY, entity.getHumidity());
		assertEquals(WIND_SPEED, entity.getWindSpeed());
		
	}

}
