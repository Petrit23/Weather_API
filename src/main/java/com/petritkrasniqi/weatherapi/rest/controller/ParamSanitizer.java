package com.petritkrasniqi.weatherapi.rest.controller;

public interface ParamSanitizer {
	public default String sanitize(String paramValue) {
		return paramValue.replaceAll("[\n\r\t]", "");
	}

}
