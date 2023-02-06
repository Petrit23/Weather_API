package com.petritkrasniqi.weatherapi.wrapper;

import com.petritkrasniqi.weatherapi.data.MetricsReportData;
import com.petritkrasniqi.weatherapi.rest.controller.MetricsReportQuery;

public class MetricsReportDataWrapper {

	private WeatherDataWrapper weatherDataWrapper;

	public MetricsReportDataWrapper(WeatherDataWrapper weatherDataWrapper) {
		this.weatherDataWrapper = weatherDataWrapper;
	}

	public MetricsReportData build(MetricsReportQuery searchQuery) {
		MetricsReportData metricsReportData = new MetricsReportData();

		metricsReportData.setSensorId(weatherDataWrapper.getSensorId());
		
		if (searchQuery.isIncludeWeatherDataPresent())
			metricsReportData.setWeatherData(weatherDataWrapper.getWeatherData());

		if (searchQuery.isTemperaturePresent())
			metricsReportData.setAverageTemperature(weatherDataWrapper.getAverageTemperature());

		if (searchQuery.isHumidityPresent())
			metricsReportData.setAverageHumidity(weatherDataWrapper.getAverageHumidity());

		if (searchQuery.isWindSpeedPresent())
			metricsReportData.setAverageWindSpeeds(weatherDataWrapper.getAverageWindSpeed());

		return metricsReportData;
	}
}
