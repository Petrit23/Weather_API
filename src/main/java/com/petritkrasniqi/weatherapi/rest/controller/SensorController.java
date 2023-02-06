package com.petritkrasniqi.weatherapi.rest.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petritkrasniqi.weatherapi.data.SensorData;
import com.petritkrasniqi.weatherapi.service.SensorService;

@RestController
public class SensorController implements BaseController {

	@Autowired
	SensorService sensorService;
	
	@GetMapping("/findSensors")
	public ResponseEntity<List<SensorData>> findSensorsById(@RequestParam (required = false) List<Long> sensorIds) {
		List<SensorData> sensors = sensorService.findSensorsById(sensorIds);
		
		return (Objects.nonNull(sensors) && !sensors.isEmpty()) ?  ResponseEntity.status(OK).body(sensors) :
			ResponseEntity.status(NO_CONTENT).body(sensors);
		
	}

	@PostMapping("/registerSensor")
	public ResponseEntity<String> registerSensor(@RequestBody SensorData newSensorInformation) {
		SensorData sensor = validateReceivedSensorData(newSensorInformation);
		
		sensorService.registerSensor(sensor);

		return ResponseEntity.status(OK).body("Sensor: \n" + sensor + " \nregistered successfully");
	}

	private SensorData validateReceivedSensorData(SensorData newSensorInformation) {
		String sanitizedSensorId = requireAndSanitize(String.valueOf(newSensorInformation.getSensorId()), Params.SENSOR_ID);
		String sanitizedCountry = requireAndSanitize(newSensorInformation.getCountry(), Params.COUNTRY);
		String sanitizedCity = requireAndSanitize(newSensorInformation.getCity(), Params.CITY);
		
		SensorData sensor = new SensorData();
		sensor.setSensorId(Long.valueOf(sanitizedSensorId));
		sensor.setCountry(sanitizedCountry);
		sensor.setCity(sanitizedCity);
		return sensor;
	}

}
