package com.petritkrasniqi.weatherapi;

import static java.util.Arrays.asList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import com.petritkrasniqi.weatherapi.data.SensorData;
import com.petritkrasniqi.weatherapi.data.WeatherData;
import com.petritkrasniqi.weatherapi.exception.BadRequestException;
import com.petritkrasniqi.weatherapi.wrapper.WeatherDataWrapper;

public abstract class WeatherAPIJunitTest {
	protected static final String WEATHER_TO_STRING = "{ sensor Id: 1234, createdDate: 2023-02-06, temperature: 10.0, humidity: 10.0, wind speed: 10.0 }";
	protected static final String SENSOR_TO_STRING = "{ sensor Id: 1234, country: COUNTRY, city: CITY }";
	protected static final Long ID = 1L;
	protected static final Long SENSOR_ID = 1234L;
	protected static final String COUNTRY = "COUNTRY";
	protected static final String CITY = "CITY";
	protected static final LocalDate CREATED_DATE = LocalDate.now();
	protected static final double TEMPERATURE = 10;
	protected static final double HUMIDITY = 10;
	protected static final double WIND_SPEED = 10;
	protected static final String EXCEPTION_MSG = "Bad Request. Returning 400 status. EXCEPTION_MSG";
	protected static SensorData sensor = new SensorData();
	protected static WeatherData weather;
	protected static List<WeatherData> weatherDataList = new ArrayList<>();
	protected static WeatherDataWrapper weatherDataWrapper; 
	protected BadRequestException ex = new BadRequestException(EXCEPTION_MSG);
	
	@BeforeAll
	public static void setUp() {
		sensor = buildSensorData();
		weather = buildWeatherData();
		weatherDataList = asList(buildWeatherData(10.0, 15.0, 20.0, LocalDate.now()), buildWeatherData(15., 20.0, 25.0, LocalDate.now().minusDays(1)));
		weatherDataWrapper = new WeatherDataWrapper(weatherDataList);		
	}
	
	protected static SensorData buildSensorData() {
		sensor.setId(ID);
		sensor.setSensorId(SENSOR_ID);
		sensor.setCountry(COUNTRY);
		sensor.setCity(CITY);

		return sensor;
	}
	
	protected static WeatherData buildWeatherData() {
		WeatherData weather = new WeatherData();
		weather.setId(ID);
		weather.setSensorId(SENSOR_ID);
		weather.setSensor(sensor);
		weather.setCreatedDate(CREATED_DATE);
		weather.setTemperature(TEMPERATURE);
		weather.setHumidity(HUMIDITY);
		weather.setWindSpeed(WIND_SPEED);
		
		return weather;
	}
	
	protected static WeatherData buildWeatherData(Double temperature, Double humidity, Double windSpeed, LocalDate createdDate) {
		WeatherData weather = new WeatherData();
		weather.setSensorId(SENSOR_ID);
		weather.setSensor(sensor);
		weather.setTemperature(temperature);
		weather.setHumidity(humidity);
		weather.setWindSpeed(windSpeed);
		weather.setCreatedDate(createdDate);
		
		return weather;
	}

}
