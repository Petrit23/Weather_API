package com.petritkrasniqi.weatherapi.repository;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;
import com.petritkrasniqi.weatherapi.application.WeatherapiApplication;
import com.petritkrasniqi.weatherapi.data.SensorData;
import com.petritkrasniqi.weatherapi.entity.Sensor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = WeatherapiApplication.class)
@DataJpaTest
public class SensorRepositoryTest extends WeatherAPIJunitTest {

	@Autowired
	SensorRepository sensorRepository;
	
	@Test
	public void noSensorsRegistered_emptyListReturned() {
		List<Sensor> sensors = sensorRepository.findAll();

		assertTrue(sensors.isEmpty());
	}

	@Test
	public void newSensorDataReceived_dataSavedToDB() {
		SensorData newSensorData = buildSensor(SENSOR_ID);
		sensorRepository.save(newSensorData.toEntity());

		List<Sensor> sensors = sensorRepository.findAll();

		assertFalse(sensors.isEmpty());
		assertEquals(1, sensors.size());
		sensors.stream().forEach(sensor -> {
			assertEquals(SENSOR_ID, sensor.getSensorId());
			assertEquals(COUNTRY, sensor.getCountry());
			assertEquals(CITY, sensor.getCity());
		});
	}
	
	@Test
	public void findSensorsById() {
		loadNewSensorsInDB();
		List<Sensor> result = sensorRepository.findSensorsById(asList(456L,789L));
		
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
	}
	
	private void loadNewSensorsInDB() {
		List<SensorData> sensors = asList(buildSensor(456L), buildSensor(789L));
		sensors.stream().forEach(sensor -> {
			sensorRepository.save(sensor.toEntity());
		});
	}

}
