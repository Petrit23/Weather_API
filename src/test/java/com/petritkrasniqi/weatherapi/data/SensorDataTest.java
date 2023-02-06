package com.petritkrasniqi.weatherapi.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;
import com.petritkrasniqi.weatherapi.entity.Sensor;

public class SensorDataTest extends WeatherAPIJunitTest {
	private SensorData copy = sensor;
	private SensorData newSensor = buildNewSensorData();

	@BeforeAll
	public static void setUp() {
		sensor = buildSensorData();
	}

	@Test
	public void toEntity() {
		assertEntity(sensor.toEntity());
	}

	@Test
	public void test_toString() {
		assertEquals(SENSOR_TO_STRING, sensor.toString());
	}

	@Test
	public void test_equals() {
		assertTrue(sensor.equals(copy));
		assertFalse(sensor.equals(newSensor));
	}

	@Test
	public void test_hashCode() {
		assertTrue(sensor.hashCode() == copy.hashCode());
		assertFalse(sensor.hashCode() == newSensor.hashCode());
	}

	private void assertEntity(Sensor entity) {
		assertEquals(ID, entity.getId());
		assertEquals(SENSOR_ID, entity.getSensorId());
		assertEquals(COUNTRY, entity.getCountry());
		assertEquals(CITY, entity.getCity());

	}

	private SensorData buildNewSensorData() {
		SensorData sensor = new SensorData();
		sensor.setId(2L);
		sensor.setSensorId(456L);
		sensor.setCountry("IRELAND");
		sensor.setCity("DUBLIN");

		return sensor;
	}

}
