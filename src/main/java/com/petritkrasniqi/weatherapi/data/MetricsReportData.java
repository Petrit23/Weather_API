package com.petritkrasniqi.weatherapi.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MetricsReportData {

	private Long sensorId;

	@JsonInclude(Include.NON_NULL)
	private Double averageTemperature;

	@JsonInclude(Include.NON_NULL)
	private Double averageHumidity;

	@JsonInclude(Include.NON_NULL)
	private Double averageWindSpeeds;

	@JsonInclude(Include.NON_NULL)
	private List<WeatherData> weatherData;

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public Double getAverageTemperature() {
		return averageTemperature;
	}

	public void setAverageTemperature(Double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}

	public Double getAverageHumidity() {
		return averageHumidity;
	}

	public void setAverageHumidity(Double averageHumidity) {
		this.averageHumidity = averageHumidity;
	}

	public Double getAverageWindSpeeds() {
		return averageWindSpeeds;
	}

	public void setAverageWindSpeeds(Double averageWindSpeeds) {
		this.averageWindSpeeds = averageWindSpeeds;
	}

	public List<WeatherData> getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(List<WeatherData> weatherData) {
		this.weatherData = weatherData;
	}

}
