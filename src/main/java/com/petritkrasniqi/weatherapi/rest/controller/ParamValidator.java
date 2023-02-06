package com.petritkrasniqi.weatherapi.rest.controller;

import static java.util.Objects.isNull;

import com.petritkrasniqi.weatherapi.exception.BadRequestException;

public interface ParamValidator {
	public default void requireParam(Object value, String paramName) {
		if (isNull(value)) {
			throw new BadRequestException("Missing required param: " + paramName);
		}
	}

}
