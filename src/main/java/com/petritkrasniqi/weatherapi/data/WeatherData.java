package com.petritkrasniqi.weatherapi.data;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.time.LocalDate;

import com.petritkrasniqi.weatherapi.entity.Weather;

public class WeatherData {

	private Long id;
	private Long sensorId;
	private SensorData sensor;
	private LocalDate createdDate;
	private Double temperature;
	private Double humidity;
	private Double windSpeed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public SensorData getSensor() {
		return sensor;
	}

	public void setSensor(SensorData sensor) {
		this.sensor = sensor;
	}

	public Weather toEntity() {
		Weather weather = new Weather();
		weather.setId(id);
		weather.setSensor(nonNull(sensor) ? sensor.toEntity() : null);
		weather.setTemperature(temperature);
		weather.setHumidity(humidity);
		weather.setWindSpeed(windSpeed);
		weather.setCreatedDate(isNull(createdDate) ? LocalDate.now() : createdDate);

		return weather;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("{ ").append("sensor Id: ").append(sensorId).append(", createdDate: ")
				.append(createdDate).append(", temperature: ").append(temperature).append(", humidity: ")
				.append(humidity).append(", wind speed: ").append(windSpeed).append(" }").toString();
	}

}
