package com.petritkrasniqi.weatherapi.util;

import static java.util.Objects.isNull;

import java.util.List;;

public class CollectionUtils {
	
	public static <T> boolean isNullOrEmpty(List<T> list) {
		return isNull(list) || list.isEmpty();
	}

}
