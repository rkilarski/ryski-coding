package com.guitariffic.dao.chord;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tmatesoft.sqljet.core.SqlJetException;

import com.guitariffic.dao.chord.ChordDbDao;
import com.guitariffic.dao.chord.DbDaoQuery;
import com.guitariffic.dao.chord.IDaoQuery;
import com.guitariffic.model.BaseChordChart;



public class ChordDbDaoTest
{

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Test
    public void testGetAllChordCharts()
    {
        ChordDbDao dbdao = new ChordDbDao();
        IDaoQuery query;
        try {
            query = new DbDaoQuery();
            List<BaseChordChart> charts = 
                    dbdao.getAllChordCharts(query, "Guitar");
            assertTrue(charts.size() > 1);
        } catch (SqlJetException e) {
            e.printStackTrace();
        }
        
    }

    @Test
    public void testGetChordChart()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetChordChartList()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertChordChart()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateChordChart()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteChordChart()
    {
        fail("Not yet implemented");
    }

}
