package com.petritkrasniqi.weatherapi.util;

import static com.petritkrasniqi.weatherapi.util.CollectionUtils.isNullOrEmpty;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class CollectionUtilsTest {
	
	
	@Test
	public void testIsNullOrEmpty() {
		assertTrue(isNullOrEmpty(new ArrayList<String>()));
		assertTrue(isNullOrEmpty(emptyList()));
		assertFalse(isNullOrEmpty(asList("1","2")));
	}
	

}
