package com.petritkrasniqi.weatherapi.service;

import static com.petritkrasniqi.weatherapi.util.CollectionUtils.isNullOrEmpty;
import static com.petritkrasniqi.weatherapi.util.LoggerUtil.logError;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petritkrasniqi.weatherapi.data.SensorData;
import com.petritkrasniqi.weatherapi.entity.Sensor;
import com.petritkrasniqi.weatherapi.exception.BadRequestException;
import com.petritkrasniqi.weatherapi.repository.SensorRepository;

@Service
public class SensorService {

	@Autowired
	SensorRepository sensorRepository;

	public List<SensorData> findSensorsById(List<Long> sensorIds) {

		return isNullOrEmpty(sensorIds) ? findAll() : findBySensorId(sensorIds);
	}

	private List<SensorData> findBySensorId(List<Long> sensorIds) {
		return sensorRepository.findSensorsById(sensorIds).stream().map(Sensor::getData).collect(toList());
	}

	private List<SensorData> findAll() {
		return sensorRepository.findAll().stream().map(Sensor::getData).collect(toList());
	}

	public void registerSensor(SensorData sensorDto) {
		Sensor sensor = sensorDto.toEntity();
		validateSensor(sensorDto);

		sensorRepository.save(sensor);
	}

	private void validateSensor(SensorData sensor) {
		List<SensorData> registeredSensors = sensorRepository.findAll().stream().map(Sensor::getData)
				.filter(existingSensorData -> existingSensorData.equals(sensor)).collect(toList());

		if (sensorAlreadyExists(registeredSensors)) {
			logError("Request to register sensor:\n " + sensor
					+ "\n failed due to sensor details already existing in the system. Rejecting attempt to register duplicate sensor");
			throw new BadRequestException("Sensor with the following details:\n " + sensor + "\n already exists");
		}

	}

	private boolean sensorAlreadyExists(List<SensorData> registeredSensors) {
		return !isNullOrEmpty(registeredSensors);
	}

}
