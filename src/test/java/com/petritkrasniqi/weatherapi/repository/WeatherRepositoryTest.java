package com.petritkrasniqi.weatherapi.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;
import com.petritkrasniqi.weatherapi.application.WeatherapiApplication;
import com.petritkrasniqi.weatherapi.data.WeatherData;
import com.petritkrasniqi.weatherapi.entity.Weather;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WeatherapiApplication.class)
@DataJpaTest
public class WeatherRepositoryTest extends WeatherAPIJunitTest {
	
	@Autowired
	WeatherRepository weatherRepository;
	
	@Test
	public void newWeatherDataReceived_dataSavedToDB() {
		WeatherData weather = buildWeatherData();
		weatherRepository.save(weather.toEntity());
		
		List<Weather> results = weatherRepository.findAll();
		
		assertFalse(results.isEmpty());
		assertEquals(1, results.size());
		results.stream().forEach(weatherData -> {
			assertEquals(SENSOR_ID, weatherData.getSensor().getSensorId());
		});
	}

//	private WeatherData buildWeatherData() {
//		WeatherData weather = new WeatherData();
//		weather.setId(ID);
//		weather.setSensorId(SENSOR_ID);
//		weather.setSensor(sensor);
//		weather.setCreatedDate(CREATED_DATE);
//		weather.setTemperature(TEMPERATURE);
//		weather.setHumidity(HUMIDITY);
//		weather.setWindSpeed(WIND_SPEED);
//
//		return weather;
//	}

}
