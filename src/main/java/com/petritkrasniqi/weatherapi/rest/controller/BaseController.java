package com.petritkrasniqi.weatherapi.rest.controller;

public interface BaseController extends ParamValidator, ParamSanitizer {

	public static class Params {
		public static final String SENSOR_ID = "sensorId";
		public static final String COUNTRY = "country";
		public static final String CITY = "city";
		public static final String TEMPERATURE = "temperature";
		public static final String HUMIDITY = "humidity";
		public static final String WIND_SPEED = "windSpeed";
		public static final String FROM_DATE = "fromDate";
		public static final String TO_DATE = "toDate";
		public static final String SENSOR_IDS = "sensorIds";
		public static final String INCLUDE_WEATHER_DATA = "includeWeatherData";
	}

	public default String requireAndSanitize(String value, String paramName) {
		requireParam(value, paramName);
		return sanitize(value);
	}

}
