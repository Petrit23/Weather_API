package com.petritkrasniqi.weatherapi.rest.controller;

import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petritkrasniqi.weatherapi.data.MetricsReportData;
import com.petritkrasniqi.weatherapi.data.WeatherData;
import com.petritkrasniqi.weatherapi.service.WeatherService;

@RestController
public class WeatherController implements BaseController {

	@Autowired
	WeatherService weatherService;

	private MetricsReportQuery.Builder queryBuilder = new MetricsReportQuery.Builder();

	@PostMapping("/weather")
	public ResponseEntity<String> parseWeatherData(@RequestBody WeatherData weather) {
		validateReceivedWeatherData(weather);

		weather = weatherService.recordWeatherData(weather);

		return ResponseEntity.status(OK).body("Successfully recorded the following weather data:\n " + weather);
	}

	@GetMapping("/metrics")
	public ResponseEntity<List<MetricsReportData>> metricsReport(
			@RequestParam(name = Params.SENSOR_IDS, required = false) List<Long> sensorIds,
			@RequestParam(name = Params.TEMPERATURE, required = false) boolean temperature,
			@RequestParam(name = Params.HUMIDITY, required = false) boolean humidity,
			@RequestParam(name = Params.WIND_SPEED, required = false) boolean windSpeed,
			@RequestParam(name = Params.FROM_DATE, required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fromDate,
			@RequestParam(name = Params.TO_DATE, required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate toDate,
			@RequestParam(name = Params.INCLUDE_WEATHER_DATA, required = false) boolean includeWeatherData) {

		MetricsReportQuery searchQuery = queryBuilder.sensorIds(sensorIds).temperature(temperature).humidity(humidity)
				.windSpeed(windSpeed).fromDate(fromDate).toDate(toDate).includeWeatherData(includeWeatherData).build();

		List<MetricsReportData> metricsReportData = weatherService.getMetricsReportData(searchQuery);

		return ResponseEntity.status(OK).body(metricsReportData);
	}

	private void validateReceivedWeatherData(WeatherData weather) {
		requireParam(weather.getTemperature(), Params.TEMPERATURE);
		requireParam(weather.getHumidity(), Params.HUMIDITY);
		requireParam(weather.getWindSpeed(), Params.WIND_SPEED);
	}

}
