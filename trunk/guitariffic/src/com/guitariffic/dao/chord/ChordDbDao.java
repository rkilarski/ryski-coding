package com.guitariffic.dao.chord;


import java.util.LinkedList;
import java.util.List;
import org.tmatesoft.sqljet.core.SqlJetException;
import com.guitariffic.model.MusicChart;
import com.thoughtworks.xstream.XStream;

/**
 * Data Access Object for Chord Charts and Database storage
 * 
 * @author rdbrmurray
 */
public class ChordDbDao implements IDaoAccess {


	@Override
	public LinkedList<MusicChart> getAllChordCharts(IDaoQuery query, String type)
			throws SqlJetException {

		List<String> list = getChordChartList(query);

		LinkedList<MusicChart> charts = DataAccessUtilities.buildChordChartList(list,
				type);

		return charts;
	}


	@Override
	public MusicChart getChordChart(IDaoQuery query, String name, String posit,
			String type) {

		MusicChart newChart = null;
		try {
			List<String> list = query.select(name, posit, false);
			if (list.size() == 1) {
				String xml = list.get(0);
				Class<? extends MusicChart> cls = DataAccessUtilities
						.getNewChordChart(type).getClass();
				XStream xstream = new XStream();
				xstream.alias(cls.getSimpleName(), cls);
				xstream.alias("BaseChordChart", MusicChart.class);
				newChart = (MusicChart) xstream.fromXML(xml);
			}
		} catch (SqlJetException e) {
			e.printStackTrace();
		}
		return newChart;

	}


	@Override
	public List<String> getChordChartList(IDaoQuery query) {

		try {
			return query.select("", "", true);
		} catch (SqlJetException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean insertChordChart(IDaoQuery query, MusicChart chart) {

		int retVal = 0;
		try {
			retVal = query.insert(chart);
		} catch (SqlJetException e) {
			e.printStackTrace();
			retVal = -1;
		}
		return retVal == 0;
	}


	@Override
	public boolean updateChordChart(IDaoQuery query, MusicChart chordChart) {

		int retVal = 0;
		try {
			retVal = query.update(chordChart);
		} catch (SqlJetException e) {
			e.printStackTrace();
			retVal = -1;
		}
		return retVal == 0;
	}


	@Override
	public boolean deleteChordChart(IDaoQuery query, MusicChart chart)
			throws SqlJetException {

		int retVal = 0;

		try {
			retVal = query.delete(chart);
		} catch (SqlJetException e) {
			e.printStackTrace();
			retVal = -1;
		}

		return retVal == 0;

	}

}