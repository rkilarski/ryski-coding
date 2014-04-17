/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.service;

import java.util.List;

import com.guitariffic.dbo.GuitarChartDBHelper;
import com.guitariffic.dbo.StorageFactory;
import com.guitariffic.model.GuitarChart;
import com.guitariffic.service.exception.GuitarChartAlreadyExists;
import com.guitariffic.service.exception.GuitarChartNotFound;

/**
 * Implementation class for the guitar chart service. This class supports
 * basic/add/edit/delete/get/list operations for guitar charts.
 */
public class GuitarChartServiceImpl extends BaseService implements GuitarChartService {
	private static GuitarChartDBHelper dao = null;

	public GuitarChartServiceImpl() {
		if (dao == null) {
			dao = StorageFactory.getGuitarChartDB(StorageFactory.STORAGE_TYPE);
		}
	}

	@Override
	public String add(GuitarChart chart) throws GuitarChartAlreadyExists {
		String id = chart.getId();
		if (dao.get(id) != null) {
			throw new GuitarChartAlreadyExists("Cannot add details of chart " + id
					+ ". This chart already exists.");
		}
		dao.add(chart);
		return getBaseURL() + "chart/" + id;
	}

	@Override
	public String update(GuitarChart chart, String id) throws GuitarChartNotFound {
		GuitarChart savedChart = dao.get(id);
		if (savedChart == null) {
			throw new GuitarChartNotFound("Details of chart " + id + " cannot be found.");
		}
		savedChart.setChordFingering(chart.getChordFingering());
		savedChart.setChordFrets(chart.getChordFrets());
		savedChart.setChordName(chart.getChordName());
		savedChart.setChordPosition(chart.getChordPosition());
		savedChart.setLeftHanded(chart.isLeftHanded());
		return getBaseURL() + "chart/" + id;
	}

	@Override
	public void delete(String id) throws GuitarChartNotFound {
		if (dao.get(id) == null) {
			throw new GuitarChartNotFound("Details of chart " + id + " cannot be found.");
		}
		dao.delete(id);
	}

	@Override
	public String[] getList(String search) {
		List<GuitarChart> charts = dao.getList(search);
		int size = charts.size();
		String[] chartsArray = new String[size];
		int i = 0;
		String baseUrl = getBaseURL();
		for (GuitarChart chart : charts) {
			String id = chart.getId();
			chartsArray[i] = baseUrl + "chart/" + id;
			i++;
		}
		return chartsArray;
	}

	@Override
	public GuitarChart get(String id) throws GuitarChartNotFound {
		GuitarChart chart = dao.get(id);
		if (chart == null) {
			throw new GuitarChartNotFound("Details of chart" + id + " cannot be found.");
		}
		return chart;
	}
}
