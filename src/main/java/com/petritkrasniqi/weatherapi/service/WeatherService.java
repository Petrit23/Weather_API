package com.petritkrasniqi.weatherapi.service;

import static com.petritkrasniqi.weatherapi.util.CollectionUtils.isNullOrEmpty;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petritkrasniqi.weatherapi.data.MetricsReportData;
import com.petritkrasniqi.weatherapi.data.WeatherData;
import com.petritkrasniqi.weatherapi.entity.Sensor;
import com.petritkrasniqi.weatherapi.entity.Weather;
import com.petritkrasniqi.weatherapi.exception.BadRequestException;
import com.petritkrasniqi.weatherapi.repository.SensorRepository;
import com.petritkrasniqi.weatherapi.repository.WeatherRepository;
import com.petritkrasniqi.weatherapi.rest.controller.MetricsReportQuery;
import com.petritkrasniqi.weatherapi.wrapper.MetricsReportDataWrapper;
import com.petritkrasniqi.weatherapi.wrapper.WeatherDataWrapper;

@Service
public class WeatherService {

	@Autowired
	SensorRepository sensorRepository;

	@Autowired
	WeatherRepository weatherRepository;

	public WeatherData recordWeatherData(WeatherData weatherData) {
		Sensor sensor = verifySensorIsRegistered(weatherData);
		Weather weather = weatherData.toEntity();
		weather.setSensor(sensor);

		weatherRepository.save(weather);

		return weather.getData();
	}

	public List<MetricsReportData> getMetricsReportData(MetricsReportQuery searchQuery) {
		List<Weather> weatherData = isSensorIdSpecified(searchQuery)
				? getWeatherDataBySesnorId(searchQuery.getSensorIds())
				: getWeatherDataForAllSensors();

		weatherData = filterForDataWithinDateRangeOrGetLatestWeatherData(weatherData, searchQuery);


		return buildMetricsReportForSensors(weatherData, searchQuery);

	}

	private List<MetricsReportData> buildMetricsReportForSensors(List<Weather> weatherData, MetricsReportQuery searchQuery) {
		Map<Long, List<WeatherData>> weatherDataGroupedBySensorId = groupDataBySesnorId(weatherData);

		return weatherDataGroupedBySensorId.keySet().stream().map(key -> weatherDataGroupedBySensorId.get(key))
				.map(WeatherDataWrapper::new).map(w -> new MetricsReportDataWrapper(w)).map(m -> m.build(searchQuery))
				.collect(toList());

	}

	private boolean isSensorIdSpecified(MetricsReportQuery searchQuery) {
		return !isNullOrEmpty(searchQuery.getSensorIds());

	}

	private List<Weather> getWeatherDataBySesnorId(List<Long> sensorIds) {
		return weatherRepository.getWeatherDataBySensorId(sensorIds);
	}

	private List<Weather> getWeatherDataForAllSensors() {
		return weatherRepository.findAll();
	}

	private Map<Long, List<WeatherData>> groupDataBySesnorId(List<Weather> weatherData) {
		return weatherData.stream().map(Weather::getData).collect(groupingBy(WeatherData::getSensorId));
	}

	private List<Weather> filterForDataWithinDateRangeOrGetLatestWeatherData(List<Weather> weatherData,
			MetricsReportQuery searchQuery) {

		return dateRangeSpecified(searchQuery) ? getWeatherDataForDateRange(weatherData, searchQuery)
				: getLatestWeatherData(weatherData);

	}

	private List<Weather> getLatestWeatherData(List<Weather> weatherData) {
		Map<Long, List<WeatherData>> weatherDataGroupedBySensorId = groupDataBySesnorId(weatherData);

		return filterForLatestData(weatherDataGroupedBySensorId);
	}

	private List<Weather> getWeatherDataForDateRange(List<Weather> weatherData, MetricsReportQuery searchQuery) {
		return weatherData.stream().filter(w -> !w.getCreatedDate().isBefore(searchQuery.getFromDate())
				&& !w.getCreatedDate().isAfter(searchQuery.getToDate())).collect(toList());
	}

	private boolean dateRangeSpecified(MetricsReportQuery searchQuery) {
		return nonNull(searchQuery.getFromDate()) && nonNull(searchQuery.getToDate());
	}

	private List<Weather> filterForLatestData(Map<Long, List<WeatherData>> weatherDataGroupedBySensorId) {
		return weatherDataGroupedBySensorId.keySet().stream().map(key -> weatherDataGroupedBySensorId.get(key))
				.map(WeatherDataWrapper::new).map(WeatherDataWrapper::getLastWeatherData).flatMap(list -> list.stream())
				.map(w -> w.toEntity()).collect(toList());
	}

	private Sensor verifySensorIsRegistered(WeatherData weatherData) {
		return sensorRepository.findAll().stream().filter(s -> s.getSensorId().equals(weatherData.getSensorId()))
				.findFirst().orElseThrow(
						() -> new BadRequestException("Received weather data from unregistered sensor\n SensorId:  "
								+ weatherData.getSensorId() + "\n Rejecting weather information supplied"));

	}

}
