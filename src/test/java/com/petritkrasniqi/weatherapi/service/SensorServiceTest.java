package com.petritkrasniqi.weatherapi.service;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;
import com.petritkrasniqi.weatherapi.data.SensorData;
import com.petritkrasniqi.weatherapi.entity.Sensor;
import com.petritkrasniqi.weatherapi.exception.BadRequestException;
import com.petritkrasniqi.weatherapi.repository.SensorRepository;

@ExtendWith(MockitoExtension.class)
public class SensorServiceTest extends WeatherAPIJunitTest {
	private static List<Sensor> mockSensors;
	private static SensorData sensorDTO;

	@InjectMocks
	private SensorService service;

	@Mock
	private SensorRepository sensorRepository;

	@BeforeAll
	public static void setUp() {
		mockSensors = asList(buildSensorEntity(SENSOR_ID), buildSensorEntity(456L), buildSensorEntity(789L));
		sensorDTO = buildSensorData();
	}

	@Test
	public void findSensorById_noIdSpecified_allSensorsReturned() {
		when(sensorRepository.findAll()).thenReturn(mockSensors);

		List<SensorData> results = service.findSensorsById(emptyList());

		assertEquals(3, results.size());
	}

	@Test
	public void findSensorById_sensorIdSpecified_onlyMatchingSensorsReturned() {
		when(sensorRepository.findSensorsById(asList(SENSOR_ID, 456L)))
				.thenReturn(asList(mockSensors.get(0), mockSensors.get(1)));

		List<SensorData> results = service.findSensorsById(asList(SENSOR_ID, 456L));

		assertEquals(2, results.size());

	}

	@Test
	public void registerNewSensor() {
		when(sensorRepository.findAll()).thenReturn(emptyList());
		when(sensorRepository.save(sensorDTO.toEntity())).thenReturn(sensorDTO.toEntity());
		
		service.registerSensor(sensorDTO);
	}
	
	@Test
	public void regsiterDuplicateSensor_ExceptionThrown() {
		when(sensorRepository.findAll()).thenReturn(asList(sensorDTO.toEntity()));
		
		try {
			service.registerSensor(sensorDTO);
		} catch (Exception e) {
			assertTrue(e instanceof BadRequestException);
			assertTrue(nonNull(e.getMessage()));
		}
	}

}
