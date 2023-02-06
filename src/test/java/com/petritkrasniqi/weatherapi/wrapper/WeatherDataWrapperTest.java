package com.petritkrasniqi.weatherapi.wrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;

public class WeatherDataWrapperTest extends WeatherAPIJunitTest {

	@Test
	public void allMetricsPresent_averageOfEachMetricCalculated() {
		assertEquals(12.5, weatherDataWrapper.getAverageTemperature());
		assertEquals(17.5, weatherDataWrapper.getAverageHumidity());
		assertEquals(22.5, weatherDataWrapper.getAverageWindSpeed());
	}

	@Test
	public void getLatestWeatherDataBasedOnCreatedDate() {

		assertEquals(1, weatherDataWrapper.getLastWeatherData().size());
		assertEquals(LocalDate.now(), weatherDataWrapper.getLastWeatherData().get(0).getCreatedDate());
	}

}
