package com.petritkrasniqi.weatherapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petritkrasniqi.weatherapi.entity.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>{
	
	@Query(value =  "SELECT s FROM Sensor s WHERE s.sensorId IN :sensorIds")
	List<Sensor> findSensorsById(@Param("sensorIds") List<Long> sensorIds);

}
