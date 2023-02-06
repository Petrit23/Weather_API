package com.petritkrasniqi.weatherapi.wrapper;

import static java.lang.Double.NaN;
import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;

import com.petritkrasniqi.weatherapi.data.WeatherData;

public class WeatherDataWrapper {

	private List<WeatherData> weatherData;

	public WeatherDataWrapper(List<WeatherData> weatherData) {
		this.weatherData = weatherData;
	}
	
	public Long getSensorId() {
		return weatherData.get(0).getSensorId();
	}

	public Double getAverageTemperature() {
		return weatherData.stream().mapToDouble(WeatherData::getTemperature).average().orElse(NaN);
	}

	public Double getAverageHumidity() {
		return weatherData.stream().mapToDouble(WeatherData::getHumidity).average().orElse(NaN);
	}

	public Double getAverageWindSpeed() {
		return weatherData.stream().mapToDouble(WeatherData::getWindSpeed).average().orElse(NaN);
	}
	
	public List<WeatherData> getWeatherData() {
		return weatherData;
	}

	public List<WeatherData> getLastWeatherData() {
		LocalDate latestDate = weatherData.stream().map(WeatherData::getCreatedDate).max(LocalDate::compareTo).get();

		return weatherData.stream().filter(w -> w.getCreatedDate().equals(latestDate)).collect(toList());
	}

}
