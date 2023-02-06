package com.petritkrasniqi.weatherapi.wrapper;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.petritkrasniqi.weatherapi.WeatherAPIJunitTest;
import com.petritkrasniqi.weatherapi.data.MetricsReportData;
import com.petritkrasniqi.weatherapi.rest.controller.MetricsReportQuery;

public class MetricsReportDataWrapperTest extends WeatherAPIJunitTest {
	private static MetricsReportDataWrapper wrapper = new MetricsReportDataWrapper(weatherDataWrapper);
	
	@Test
	public void buildMetricReport_allQueryParamsSpecifiedInSearchQuery() {
		MetricsReportData reportData = wrapper.build(queryBuilder().sensorIds(asList(SENSOR_ID)).temperature(true).humidity(true).windSpeed(true).includeWeatherData(true).build());
		
		assertEquals(SENSOR_ID, reportData.getSensorId());
		assertEquals(12.5, reportData.getAverageTemperature());
		assertEquals(17.5, reportData.getAverageHumidity());
		assertEquals(22.5, reportData.getAverageWindSpeeds());
		
	}
	
	@Test
	public void noQueryParamsSpecifiedInSearchQuery_allSensorsQueried_SensorIdReturned() {
		MetricsReportData reportData = wrapper.build(queryBuilder().build());
		
		assertEquals(SENSOR_ID, reportData.getSensorId());
		assertEquals(null, reportData.getWeatherData());
		assertEquals(null, reportData.getAverageTemperature());
		assertEquals(null, reportData.getAverageHumidity());
		assertEquals(null, reportData.getAverageWindSpeeds());
	}
	
	private MetricsReportQuery.Builder queryBuilder() {
		return new MetricsReportQuery.Builder();
	}

}
