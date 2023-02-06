package com.petritkrasniqi.weatherapi.data;

import java.util.Objects;

import com.petritkrasniqi.weatherapi.entity.Sensor;

public class SensorData {

	private Long id;
	private Long sensorId;
	private String country;
	private String city;

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
	
	public Sensor toEntity() {
		Sensor sensor = new Sensor();
		sensor.setId(id);
		sensor.setSensorId(sensorId);
		sensor.setCountry(country);
		sensor.setCity(city);
		
		return sensor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sensorId, country, city);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SensorData) {
			SensorData other = (SensorData) obj;
			return Objects.equals(sensorId, other.sensorId) && Objects.equals(country, other.country) && Objects.equals(city, other.city);
		}
		return false;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("{ ").append("sensor Id: ").append(sensorId).append(", country: ").append(country)
				.append(", city: ").append(city).append(" }").toString();
	}

}
