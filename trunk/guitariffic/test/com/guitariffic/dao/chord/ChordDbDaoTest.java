package com.guitariffic.dao.chord;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tmatesoft.sqljet.core.SqlJetException;

import com.guitariffic.model.MusicChart;

public class ChordDbDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetAllChordCharts() {
		ChordDbDao dbdao = new ChordDbDao();
		IDaoQuery query;
		try {
			query = new DbDaoQuery();
			List<MusicChart> charts = dbdao.getAllChordCharts(query, "Guitar");
			assertTrue(charts.size() > 1);
		} catch (SqlJetException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetChordChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChordChartList() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertChordChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateChordChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteChordChart() {
		fail("Not yet implemented");
	}

}
