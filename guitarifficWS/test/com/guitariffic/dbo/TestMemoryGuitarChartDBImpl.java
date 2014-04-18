/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.dbo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.guitariffic.model.GuitarChart;

/**
 * JUnit to test out the guitar chart memory API.
 */
public class TestMemoryGuitarChartDBImpl {
    private static GuitarChartDBHelper dao = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao = StorageFactory.getGuitarChartDB(StorageFactory.STORAGE_TYPE);
    }

    @Test
    public void testAdd() {
        GuitarChart chart = new GuitarChart();
        chart.setChordName("R");
        chart.setChordFingering("654321");
        chart.setChordFrets("123456");
        chart.setChordPosition("3");
        chart.setLeftHanded(true);
        String id = dao.add(chart);
        assertTrue("No id was returned", Integer.parseInt(id) > 0);

        GuitarChart newChart = dao.get(id);
        assertTrue("No chart was returned", newChart != null);
        printChart(newChart);
    }

    @Test
    public void testDelete() {
        GuitarChart chart = dao.get("5");
        assertTrue("No chart was returned", chart != null);
        dao.delete("5");

        GuitarChart chartDeleted = dao.get("5");
        assertTrue("Chart was returned", chartDeleted == null);
    }

    @Test
    public void testGet() {
        GuitarChart chart = dao.get("3");
        assertTrue("No chart was returned", chart != null);
        printChart(chart);
    }

    @Test
    public void testGetAll() {
        List<GuitarChart> list = dao.getList("");
        assertTrue("Missing guitar charts.", list.size() > 0);
        for (GuitarChart chart : list) {
            System.out.println(chart.getChordName());
        }
    }

    @Test
    public void testGetAllGSharpChords() {
        String G = "G#";
        List<GuitarChart> list = dao.getList(G);
        for (GuitarChart chart : list) {
            assertTrue("This is returning chords other than " + G, chart.getChordName()
                    .toUpperCase().contains(G));
            System.out.println(chart.getChordName());
        }
    }

    @Test
    public void testUpdate() {
        String newFingering = "951753";
        GuitarChart chart = dao.get("3");
        assertTrue("No chart was returned", chart != null);
        printChart(chart);
        chart.setChordFingering(newFingering);
        dao.update(chart, "3");

        GuitarChart newChart = dao.get("3");
        assertTrue("Fingering wasn't updated!", newChart.getChordFingering().equals(newFingering));
        printChart(newChart);
    }

    private static void printChart(GuitarChart chart) {
        System.out.println("Name: " + chart.getChordName());
        System.out.println("Fingering: " + chart.getChordFingering());
        System.out.println("Frets: " + chart.getChordFrets());
        System.out.println("Position: " + chart.getChordPosition());
        System.out.println("Left Handed: " + chart.isLeftHanded());
        System.out.println(" ");
    }
}
