package com.guitariffic.dbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.guitariffic.model.GuitarChart;

public class MemoryGuitarChartDBImpl implements GuitarChartDBHelper {
    private static Map<String, GuitarChart> map = null;
    private static GuitarChartDBHelper instance;

    public MemoryGuitarChartDBImpl() {
        if (map == null) {
            map = new HashMap<String, GuitarChart>();
        }
    }

    public static GuitarChartDBHelper getInstance() {
        if (instance == null) {
            instance = new MemoryGuitarChartDBImpl();
            map = new HashMap<String, GuitarChart>();
        }
        return instance;
    }

    @Override
    public void add(GuitarChart chart) {
        map.put(chart.getId(), chart);
    }

    @Override
    public void update(GuitarChart chart, String id) {
        map.put(id, chart);
    }

    @Override
    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public List<GuitarChart> getList(String search) {
        List<GuitarChart> list = new ArrayList<GuitarChart>();
        Iterator<Entry<String, GuitarChart>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, GuitarChart> pairs = it.next();
            list.add(pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        return list;
    }

    @Override
    public GuitarChart get(String id) {
        return map.get(id);
    }
}
