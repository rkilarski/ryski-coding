package com.guitariffic.controller;

import java.awt.Frame;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.tmatesoft.sqljet.core.SqlJetException;

import com.guitariffic.controller.filter.GreaterThanName;
import com.guitariffic.controller.filter.GreaterThanPosition;
import com.guitariffic.controller.filter.NameFilter;
import com.guitariffic.controller.filter.PositionFilter;
import com.guitariffic.dao.chord.AbstractDaoFactory;
import com.guitariffic.dao.chord.ChordDaoFactory;
import com.guitariffic.dao.chord.IDaoAccess;
import com.guitariffic.dao.chord.IDaoQuery;
import com.guitariffic.model.ChordTrayTableModel;
import com.guitariffic.model.GuitarChordChart;
import com.guitariffic.model.MusicChart;
import com.guitariffic.view.GuitarChordChartEditor;

/**
 * Controller class for the chord tray area.
 * 
 * @author ryszardkilarski
 * 
 */
public class ChordTrayController {
	private ChordTrayTableModel chordTray; // Chord tray model.
	private ChordDaoFactory client;
	private AbstractDaoFactory daofactory;
	private IDaoAccess dbDao;
	private IDaoQuery dbQuery;
	private LinkedList<MusicChart> allChordCharts;

	public ChordTrayController() {

		allChordCharts = new LinkedList<MusicChart>();
		chordTray = new ChordTrayTableModel();
		client = new ChordDaoFactory();

		// AbstractDaoFactory
		daofactory = client.buildFactory(AbstractDaoFactory.database);

		try {
			dbDao = daofactory.createDaoAccess();
			dbQuery = daofactory.createDaoQuery();
			List<MusicChart> charts = dbDao.getAllChordCharts(dbQuery, "Guitar");

			for (MusicChart chart : charts) {
				allChordCharts.add(chart);
			}

		} catch (SqlJetException e) {
			e.printStackTrace();
		}

		Collections.sort(allChordCharts, new GreaterThanPosition());
		Collections.sort(allChordCharts, new GreaterThanName());
		chordTray.setChordChartList(allChordCharts);
	}

	public ChordTrayTableModel getChordTray() {
		return chordTray;
	}

	public void setChordTray(ChordTrayTableModel chordTray) {
		this.chordTray = chordTray;
	}

	/**
	 * Filter chord chart display
	 * 
	 * @param searchName
	 * @param searchPosition
	 */
	public void updateChords(String searchName, String searchPosition) {
		// If we need to reset the list, just display the full chord chart list.
		if ((searchName == null) || (searchName == "") || (searchName.equals("filter..."))
				&& (searchPosition == null || searchPosition == "")) {
			chordTray.setChordChartList(allChordCharts);
		} else {

			NameFilter namefilter = new NameFilter();
			namefilter.setFilter(null, searchName);

			PositionFilter posfilter = new PositionFilter();
			posfilter.setFilter(namefilter, searchPosition);
			LinkedList<MusicChart> newChordChartsx = posfilter.filterList(allChordCharts);
			chordTray.setChordChartList(newChordChartsx);
		}
	}

	/**
	 * Create new chord chart. Save to database and display.
	 * 
	 * @param frame
	 */
	public void newChordChart(Frame frame) {
		GuitarChordChartEditor editor = new GuitarChordChartEditor(frame);
		editor.setVisible(true);
		GuitarChordChart guitarChordChart = editor.getGuitarChordChart();

		if (guitarChordChart != null) {
			// add to database
			try {
				daofactory = client.buildFactory(AbstractDaoFactory.database);
				dbDao = daofactory.createDaoAccess();
				dbQuery = daofactory.createDaoQuery();

				dbDao.insertChordChart(dbQuery, guitarChordChart);

				LinkedList<MusicChart> chordChartsx = chordTray.getChordChartList();
				chordChartsx.add(guitarChordChart);

				// sort list
				Collections.sort(chordChartsx, new GreaterThanPosition());
				Collections.sort(chordChartsx, new GreaterThanName());

				chordTray.setChordChartList(chordChartsx);

			} catch (SqlJetException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Export all chord charts to files
	 * 
	 * @param frame
	 */
	public void exportAllChordCharts(Frame frame) {
		LinkedList<MusicChart> chordCharts = chordTray.getChordChartList();
		try {
			/* AbstractDaoFactory */
			daofactory = client.buildFactory(AbstractDaoFactory.file);
			dbDao = daofactory.createDaoAccess();
			dbQuery = daofactory.createDaoQuery();

			for (MusicChart c : chordCharts) {
				dbDao.insertChordChart(dbQuery, c);
			}
		} catch (SqlJetException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Export chord chart to file
	 * 
	 * @param frame
	 * @param chart
	 */
	public void exportChordChart(Frame frame, MusicChart chart) {
		try {
			/* AbstractDaoFactory */
			daofactory = client.buildFactory(AbstractDaoFactory.file);
			dbDao = daofactory.createDaoAccess();
			dbQuery = daofactory.createDaoQuery();

			dbDao.insertChordChart(dbQuery, chart);

		} catch (SqlJetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete chord chart from database
	 * 
	 * @param frame
	 * @param chart
	 */
	public void deleteChordChart(Frame frame, MusicChart chart) {
		// confirm Y/N delete
		int response =
				JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete chord "
						+ chart.getChordName() + " at position " + chart.getChordPosition() + "?");

		if (response == JOptionPane.YES_OPTION) {
			try {
				/* AbstractDaoFactory */
				daofactory = client.buildFactory(AbstractDaoFactory.database);
				dbDao = daofactory.createDaoAccess();
				dbQuery = daofactory.createDaoQuery();

				// delete from database
				dbDao.deleteChordChart(dbQuery, chart);
				// delete from display
				removeChartFromList(chart);

			} catch (SqlJetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Remove chord chart from display
	 * 
	 * @param chart
	 */
	public void removeChartFromList(MusicChart chart) {
		allChordCharts.remove(chart);
		Collections.sort(allChordCharts, new GreaterThanPosition());
		Collections.sort(allChordCharts, new GreaterThanName());
		chordTray.setChordChartList(allChordCharts);
	}

	/**
	 * Edit chord chart
	 * 
	 * @param frame
	 * @param chart
	 */
	public void editChart(Frame frame, MusicChart chart) {
		allChordCharts.remove(chart);

		GuitarChordChartEditor chordChartEditor = new GuitarChordChartEditor(frame);
		chordChartEditor.setGuitarChordChart((GuitarChordChart) chart);
		chordChartEditor.setVisible(true);
		// Make the renderer reappear.
		if (chordChartEditor.getGuitarChordChart() != null) {
			chart = chordChartEditor.getGuitarChordChart();
		}
		allChordCharts.add(chart);
		Collections.sort(allChordCharts, new GreaterThanPosition());
		Collections.sort(allChordCharts, new GreaterThanName());
		chordTray.setChordChartList(allChordCharts);
	}

}
