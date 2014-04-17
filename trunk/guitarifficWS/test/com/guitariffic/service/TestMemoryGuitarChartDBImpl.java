package com.guitariffic.service;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestMemoryGuitarChartDBImpl {
	private static GuitarChartService service = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new GuitarChartServiceImpl();
	}

	@Test
	public void testGetAll() {
		String[] list = service.getList("");
		assertTrue("we have guitar charts!", list.length > 0);
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}
}
