/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.dbo;

import java.util.List;

import com.guitariffic.model.GuitarChart;

/**
 * Future functionality to use Sql database.
 */
public class GuitarChartSqlDBImpl implements GuitarChartDBHelper {
    private static GuitarChartDBHelper instance;

    public static GuitarChartDBHelper getInstance() {
        if (instance == null) {
            instance = new GuitarChartSqlDBImpl();
        }
        return instance;
    }

    @Override
    public String add(GuitarChart object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public GuitarChart get(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GuitarChart> getList(String search) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(GuitarChart object, String id) {
        // TODO Auto-generated method stub

    }

}
