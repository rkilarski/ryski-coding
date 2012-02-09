package com.guitariffic.dao.chord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;

import com.guitariffic.model.MusicChart;
import com.guitariffic.model.GuitarChordChart;
import com.thoughtworks.xstream.XStream;

/**
 * Utility class for Chord Chart; Uses com.thoughtworks.xstream.XStream 
 * 
 * @author rdbrmurray
 *
 */
public class DataAccessUtilities
{

    /**
     * 
     * Get chart as XML string
     * @param chart
     * @return String XML
     */
	public static String getXStream(MusicChart chart)
	{

		Class<? extends MusicChart> cls = chart.getClass();
		XStream xstreamx = new XStream();

		xstreamx.alias(cls.getSimpleName(), cls);
		xstreamx.alias("BaseChordChart", MusicChart.class);
		String xml = xstreamx.toXML(chart);

		return xml;

	}

	/**
	 * Validate chord chart: has name and has position
	 * @param chart
	 * @return valid chord object
	 */
	public static boolean validChord(MusicChart chart)
	{
		return !(chart.getChordName().isEmpty() || chart.getChordPosition().isEmpty());
	}

	/**
	 * Generate KeyID from name and position. Uses CRC32 algorithm to generate key
	 * 
	 * @param name
	 * @param position
	 * @return key
	 */
	public static long getKeyId(String name, String position)
	{
		CRC32 crc = new CRC32();
		crc.reset();
		byte[] b = DataAccessUtilities.combine(name.toUpperCase().getBytes(), position.toUpperCase().getBytes());
		crc.update(b);
		long key = crc.getValue();
		return key;
	}

	/*
	 * Combine name and position byte arrays
	 */
	/**
	 * Combine two byte arrays
	 * @param one
	 * @param two
	 * @return byte array
	 */
	private static byte[] combine(byte[] one, byte[] two)
	{

		byte[] combined = new byte[one.length + two.length];

		for (int i = 0; i < combined.length; ++i)
		{
			combined[i] = i < one.length ? one[i] : two[i - one.length];
		}
		return combined;
	}

	/**
	 * Create chord cart from core information
	 * @param type
	 * @param name
	 * @param posit
	 * @param finger
	 * @param frets
	 * @return BaseChordChart
	 */
	public static MusicChart createChordChart(String type, String name, String posit, String finger, String frets)
	{

		MusicChart chart = null;
		if (type == "guitar")
		{
			chart = new GuitarChordChart();
		} else if (type == "piano")
		{
			return chart; // not implemented
		}

		long key = getKeyId(name, posit);

		chart.setKey(key);
		chart.setChordName(name);
		chart.setChordPosition(posit);
		chart.setChordFingering(finger);
		chart.setChordFrets(frets);
		chart.setDirty(false);
		return chart;
	}

	/**
	 * Generate linked list from list of XML chord chart strings
	 * 
	 * @param list
	 * @param type
	 * @return LinkedList
	 */
	public static LinkedList<MusicChart> buildChordChartList(List<String> list, String type)
	{

		XStream xstream = null;
		Class<? extends MusicChart> cls = DataAccessUtilities.getNewChordChart(type).getClass();

		LinkedList<MusicChart> charts = new LinkedList<MusicChart>();

		Iterator<String> itr = list.iterator();
		while (itr.hasNext())
		{

			// XML string to object
			// http://xstream.codehaus.org/tutorial.html
			xstream = new XStream();
			xstream.alias(cls.getSimpleName(), cls);
			xstream.alias("BaseChordChart", MusicChart.class);

			String text;

			if (!itr.hasNext())
			{
				return charts;
			}

			while (itr.hasNext())
			{
				text = itr.next().toString();
				if (text.isEmpty())
					continue;

				MusicChart chart2 = (MusicChart) xstream.fromXML(text);
				charts.add(chart2);
			}
		}
		return charts;
	}

	/**
	 * Generate BaseChordChart from XML string
	 * @param type
	 * @return BaseChordChart
	 */
	public static MusicChart getNewChordChart(String type)
	{
		if (type == "Guitar")
			return new GuitarChordChart();
		else
			return null;
	}

	private static final String[] chordNames = { "D", "D7", "Dm", "Dm7", "A", "A7", "Am", "Am7", "C", "C7", "Cm", "Cm7", "E", "Em", "E7", "Em7", "G",
			"G7" };
	private static final String[] chordPosit = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" };
	private static final String[] chordFingr = { "X  132", "X  213", "X  231", "X  211", "X 123 ", "X 1 3 ", "X 231 ", "X 2 1 ", "X32 1 ", "X3241 ",
			"X41 2X", "X3141X", " 231  ", " 23   ", " 2314 ", " 23 4 ", "21   3", "32   1" };
	private static final String[] chordFrets = { "   232", "   212", "   231", "   231", "  222 ", "  2 2 ", "  221 ", "  2 1 ", " 32 1 ", " 3231 ",
			" 31 2 ", " 3131 ", " 221  ", " 22   ", " 2213 ", " 22 3 ", "32   3", "32   1" };

	/**
	 * Get initial list of BaseChordCharts 
	 * 
	 * @return List<BaseChordChart>
	 */
	public static List<MusicChart> getNewChordChartList()
	{
		List<MusicChart> list = new ArrayList<MusicChart>();

		for (int x = 0; x < chordNames.length; x++)
		{
			MusicChart chart = DataAccessUtilities.createChordChart("guitar", chordNames[x], chordPosit[x], chordFingr[x], chordFrets[x]);
			//String xml = DataAccessUtilities.getXStream(chart);

			list.add(chart);
		}
		list = generateFFormBarreChords(list);
		list = generateAFormBarreChords(list);
		return list;
	}

	private static List<MusicChart> generateFFormBarreChords(List<MusicChart> list)
	{
		String majorFingering = "134211";
		String majorFrets = "133211";
		String minorFingering = "134111";
		String minorFrets = "133111";
		String seventhFingering = "131241";
		String seventhFrets = "131241";
		String seventhMinorFingering = "131141";
		String seventhMinorFrets = "131141";

		String[] chordNameSharpList = { "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G" };
		String[] chordPositionSharpList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
		// String[] chordNameFlatList = { "F", "Gb", "G", "Ab", "A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb" }; // , "G" };
		String[] chordNameFlatList = { "Gb", "Ab", "Bb", "Db", "Eb", "Gb" }; // , "G" };
		String[] chordPositionFlatList = { "2", "4", "6", "9", "11", "14" };

		list = generateBarreChords(chordNameSharpList, "", majorFingering, majorFrets, chordPositionSharpList, list);
		list = generateBarreChords(chordNameSharpList, "m", minorFingering, minorFrets, chordPositionSharpList, list);
		list = generateBarreChords(chordNameSharpList, "7", seventhFingering, seventhFrets, chordPositionSharpList, list);
		list = generateBarreChords(chordNameSharpList, "m7", seventhMinorFingering, seventhMinorFrets, chordPositionSharpList, list);

		list = generateBarreChords(chordNameFlatList, "", majorFingering, majorFrets, chordPositionFlatList, list);
		list = generateBarreChords(chordNameFlatList, "m", minorFingering, minorFrets, chordPositionFlatList, list);
		list = generateBarreChords(chordNameFlatList, "7", seventhFingering, seventhFrets, chordPositionFlatList, list);
		list = generateBarreChords(chordNameFlatList, "m7", seventhMinorFingering, seventhMinorFrets, chordPositionFlatList, list);

		return list;
	}

	private static List<MusicChart> generateAFormBarreChords(List<MusicChart> list)
	{
		String majorFingering = "113331";
		String majorFrets = "113331";
		String minorFingering = "113421";
		String minorFrets = "113321";
		String seventhFingering = "113141";
		String seventhFrets = "113131";
		String seventhMinorFingering = "113121";
		String seventhMinorFrets = "113121";

		String[] chordNameSharpList = { "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C" };
		String[] chordPositionSharpList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
		// String[] chordNameFlatList = { "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb" }; // , "B", "C" };
		String[] chordNameFlatList = { "Bb", "Db", "Eb", "Gb", "Ab", "Bb" }; // , "B", "C" };
		String[] chordPositionFlatList = { "1", "4", "6", "9", "11", "13" };

		list = generateBarreChords(chordNameSharpList, "", majorFingering, majorFrets, chordPositionSharpList, list);
		list = generateBarreChords(chordNameSharpList, "m", minorFingering, minorFrets, chordPositionSharpList, list);
		list = generateBarreChords(chordNameSharpList, "7", seventhFingering, seventhFrets, chordPositionSharpList, list);
		list = generateBarreChords(chordNameSharpList, "m7", seventhMinorFingering, seventhMinorFrets, chordPositionSharpList, list);

		list = generateBarreChords(chordNameFlatList, "", majorFingering, majorFrets, chordPositionFlatList, list);
		list = generateBarreChords(chordNameFlatList, "m", minorFingering, minorFrets, chordPositionFlatList, list);
		list = generateBarreChords(chordNameFlatList, "7", seventhFingering, seventhFrets, chordPositionFlatList, list);
		list = generateBarreChords(chordNameFlatList, "m7", seventhMinorFingering, seventhMinorFrets, chordPositionFlatList, list);
		return list;
	}

	private static List<MusicChart> generateBarreChords(String[] chordNames, String suffix, String chordFingering, String chordFrets, String[] chordPositions, List<MusicChart> list)
	{
		for (int x = 0; x < chordNames.length; x++)
		{
			MusicChart chart =
					DataAccessUtilities.createChordChart("guitar", chordNames[x] + suffix, chordPositions[x], chordFingering, chordFrets);
			list.add(chart);
		}
		return list;
	}
}
