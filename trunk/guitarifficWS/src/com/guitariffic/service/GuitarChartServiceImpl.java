package com.guitariffic.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.guitariffic.model.GuitarChart;
import com.guitariffic.service.exception.GuitarChartAlreadyExists;
import com.guitariffic.service.exception.GuitarChartNotFound;

public class GuitarChartServiceImpl extends BaseService implements GuitarChartService {
    private Map<String, GuitarChart> map = new HashMap<String, GuitarChart>();

    @Override
    public String add(GuitarChart chart) throws GuitarChartAlreadyExists {
        String id = chart.getId();
        if (map.get(id) != null) {
            throw new GuitarChartAlreadyExists("Cannot add details of chart " + id
                    + ". This chart already exists.");
        }
        map.put(id, chart);
        return getBaseURL() + "chart/" + id;
    }

    @Override
    public String update(GuitarChart chart, String id) throws GuitarChartNotFound {
        GuitarChart savedChart = map.get(id);
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
        if (map.get(id) == null) {
            throw new GuitarChartNotFound("Details of chart " + id + " cannot be found.");
        }
        map.remove(id);
    }

    @Override
    public String[] getList(String search) {
        int size = map.size();
        String[] charts = new String[size];
        Iterator<String> iterator = map.keySet().iterator();
        int i = 0;
        String baseUrl = getBaseURL();

        while (iterator.hasNext()) {
            String chartId = iterator.next();
            charts[i] = baseUrl + "chart/" + chartId;
            i++;
        }
        return charts;
    }

    @Override
    public GuitarChart get(String id) throws GuitarChartNotFound {
        GuitarChart chart = map.get(id);
        if (chart == null) {
            throw new GuitarChartNotFound("Details of chart" + id + " cannot be found.");
        }
        return chart;
    }
}
