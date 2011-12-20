package com.guitariffic.dao.chord;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.tmatesoft.sqljet.core.SqlJetException;

import com.guitariffic.model.BaseChordChart;
import com.guitariffic.model.GuitarChordChart;

public class DbDaoQueryTest
{

    private static final String DB_NAME = "database-test\\guitariffic.sqljet";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        File dbFile = new File(DB_NAME);
        if (dbFile.exists()) {
            // create and initialize
            dbFile.delete();
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    /**
     * Test method for {@link com.guitariffic.dao.DbDaoQuery#DbDaoQuery()}.
     * 
     * @throws SqlJetException
     */
    @Test
    public void testDbDaoQuery() throws SqlJetException {
        DbDaoQuery query = new DbDaoQuery();
        assertTrue (query != null);
    }
    
    /**
     * Test method for
     * {@link com.guitariffic.dao.DbDaoQuery#select(java.lang.String)}.
     * 
     * @throws SqlJetException
     */
    @Test
    public void testSelect() throws SqlJetException {
        // fail("Not yet implemented");
        DbDaoQuery query = new DbDaoQuery();

        List<String> chords = query.select("A", "", true);

        assertTrue (query != null);
        assertTrue (chords.size() > 0);

        chords = query.select("", "", true);

        assertTrue (query != null);
        assertTrue (chords.size() > 0);

    }

    /**
     * Test method for
     * {@link com.guitariffic.dao.DbDaoQuery#insert(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testInsert() throws SqlJetException {
        // fail("Not yet implemented");
        DbDaoQuery query = new DbDaoQuery();

        // empty chart cannot be inserted
        BaseChordChart chart = new GuitarChordChart();
        chart.setChordName("");
        chart.setChordPosition("");
        chart.setChordFingering("");
        chart.setChordFrets("");
        chart.setDirty(false);

        int retVal = query.insert(chart);

        assertTrue (query != null);
        assertTrue (retVal != 0);

        // filled chart successful
        chart = new GuitarChordChart();
        chart.setChordName("Cmaj7");
        chart.setChordPosition("1");
        chart.setChordFingering("XXX23X");
        chart.setChordFrets("XXX23X");
        chart.setDirty(false);

        retVal = query.insert(chart);

        assertTrue (query != null);
        assertTrue (retVal == 0);

        /*
         * invalid insert of identical
         */
        retVal = query.insert(chart);
        assertTrue (retVal != 0);

    }
    
    /**
     * Test method for
     * {@link com.guitariffic.dao.DbDaoQuery#update(java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testUpdate() throws SqlJetException {
        // fail("Not yet implemented");
        DbDaoQuery query = new DbDaoQuery();
        int retVal;

        // existing chart successful
        BaseChordChart chart = new GuitarChordChart();
        chart.setChordName("C");
        chart.setChordPosition("1");
        chart.setChordFingering("XXX23X");
        chart.setChordFrets("XXX23X");
        chart.setDirty(false);

        retVal = query.update(chart);
        assertTrue (retVal == 0);

        // invalid update of non-existing record
        chart = new GuitarChordChart();
        chart.setChordName("C7b5");
        chart.setChordPosition("3");
        chart.setChordFingering("XXX23X");
        chart.setChordFrets("XXX23X");
        chart.setDirty(false);

        retVal = query.update(chart);
        assertTrue (retVal != 0);

        // invalid update of empty record
        chart = new GuitarChordChart();
        chart.setChordName("");
        chart.setChordPosition("");
        chart.setChordFingering("XXX23X");
        chart.setChordFrets("XXX23X");
        chart.setDirty(false);

        retVal = query.update(chart);
        assertTrue (retVal != 0);

    }

    /**
     * Test method for
     * {@link com.guitariffic.dao.DbDaoQuery#delete(java.lang.String)}.
     */
    @Test
    public void testDelete() throws SqlJetException {
        // fail("Not yet implemented");
        DbDaoQuery query = new DbDaoQuery();
        int retVal = 0;

        BaseChordChart chart = new GuitarChordChart();
        chart.setChordName("C7b5");
        chart.setChordPosition("3");
        chart.setChordFingering("XXX23X");
        chart.setChordFrets("XXX23X");
        chart.setDirty(false);

        retVal = query.insert(chart);
        assertTrue (retVal != 0);
        
        // confirm present
        List<String> xml = new ArrayList<String>();
        xml = query.select(chart.getChordName(), chart.getChordPosition(),
                false);
        assertTrue (xml != null && xml.size() > 0);

        retVal = query.delete(chart);
        assertTrue (retVal == 0);
        // assert not present
        xml.clear();
        xml = query.select(chart.getChordName(), chart.getChordPosition(),
                false);
        assertTrue (xml != null && xml.size() == 0);
        

        // invalid update of empty record
        chart = new GuitarChordChart();
        chart.setChordName("");
        chart.setChordPosition("");
        chart.setChordFingering("XXX23X");
        chart.setChordFrets("XXX23X");
        chart.setDirty(false);

        retVal = query.delete(chart);
        assertTrue (retVal != 0);

    }
    

}
