package com.petritkrasniqi.weatherapi.entity;

import static java.util.Objects.isNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.petritkrasniqi.weatherapi.data.WeatherData;

@Entity
@Table(name = "weather")
public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
	private Sensor sensor;

	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "temperature")
	private Double temperature;

	@Column(name = "humidity")
	private Double humidity;

	@Column(name = "windSpeed")
	private Double windSpeed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
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

	public WeatherData getData() {
		WeatherData weatherData = new WeatherData();
		weatherData.setId(id);
		weatherData.setSensor(sensor.getData());
		weatherData.setSensorId(sensor.getSensorId());
		weatherData.setCreatedDate(isNull(createdDate) ? LocalDate.now() : createdDate);
		weatherData.setTemperature(temperature);
		weatherData.setHumidity(humidity);
		weatherData.setWindSpeed(windSpeed);

		return weatherData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sensor, createdDate, temperature, humidity, windSpeed);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Weather) {
			Weather other = (Weather) obj;
			return Objects.equals(sensor, other.sensor) && Objects.equals(createdDate, other.createdDate)
					&& Objects.equals(temperature, other.temperature) && Objects.equals(humidity, other.humidity)
					&& Objects.equals(windSpeed, other.windSpeed);
		}
		return false;
	}

}
