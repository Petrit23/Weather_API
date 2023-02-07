package com.petritkrasniqi.weatherapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.petritkrasniqi.weatherapi.data.SensorData;

@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "sensor_id", unique = true)
	private Long sensorId;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@OneToMany(mappedBy = "sensor")
	private List<Weather> weatherData = new ArrayList<>();

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Weather> getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(List<Weather> weatherData) {
		this.weatherData = weatherData;
	}

	public SensorData getData() {
		SensorData sensorData = new SensorData();
		sensorData.setId(id);
		sensorData.setSensorId(sensorId);
		sensorData.setCountry(country);
		sensorData.setCity(city);

		return sensorData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sensorId, country, city);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sensor) {
			Sensor other = (Sensor) obj;
			return Objects.equals(sensorId, other.sensorId) && Objects.equals(country, other.country)
					&& Objects.equals(city, other.city);
		}
		return false;
	}

}
