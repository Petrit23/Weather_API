package com.petritkrasniqi.weatherapi.service;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;
import com.petritkrasniqi.weatherapi.data.WeatherData;
import com.petritkrasniqi.weatherapi.entity.Sensor;
import com.petritkrasniqi.weatherapi.exception.BadRequestException;
import com.petritkrasniqi.weatherapi.repository.SensorRepository;
import com.petritkrasniqi.weatherapi.repository.WeatherRepository;
import com.petritkrasniqi.weatherapi.rest.controller.MetricsReportQuery;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest extends WeatherAPIJunitTest {

	@InjectMocks
	WeatherService service;

	@Mock
	WeatherRepository weatherRepository;

	@Mock
	SensorRepository sensorRepository;

	@Test
	public void recordWeatherData() {
		Sensor sensor = buildSensorEntity(SENSOR_ID);
		WeatherData weatherData = buildWeatherData();
		when(sensorRepository.findAll()).thenReturn(asList(sensor));
		when(weatherRepository.save(weatherData.toEntity())).thenReturn(weatherData.toEntity());

		service.recordWeatherData(weatherData);
	}

	@Test
	public void recordWeatherData_sensorNotRegistered_exceptionThrown() {
		WeatherData weatherData = buildWeatherData();
		when(sensorRepository.findAll()).thenReturn(emptyList());

		try {
			service.recordWeatherData(weatherData);
		} catch (Exception e) {
			assertTrue(e instanceof BadRequestException);
			assertTrue(nonNull(e.getMessage()));

		}

	}
	
	private MetricsReportQuery.Builder queryBuilder() {
		return new MetricsReportQuery.Builder();
	}

}
