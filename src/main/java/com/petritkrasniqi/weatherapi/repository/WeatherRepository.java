package com.petritkrasniqi.weatherapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petritkrasniqi.weatherapi.entity.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long>, JpaSpecificationExecutor<Weather> {
	
	@Query(value = "Select w FROM Weather w where w.sensor.sensorId IN :sensorIds")
	public List<Weather> getWeatherDataBySensorId(@Param("sensorIds") List<Long> sensorIds);
	


}
