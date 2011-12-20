package com.guitariffic.dao.chord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.tmatesoft.sqljet.core.SqlJetException;
import com.guitariffic.model.BaseChordChart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

public class FileDaoQuery implements IDaoQuery {

	private static final String DB_NAME = "datafiles\\";

	private static final String filePrefix = "guitariffic";
	private static final String fileExtension = "xml";

	// http://www.java2s.com/Code/JavaAPI/java.io/FilelistFilesFilenameFilterfilter.htm
	class FileListFilter implements FilenameFilter {
		private String name;
		private String extension;

		public FileListFilter(String name, String extension) {
			this.name = name;
			this.extension = extension;
		}

		public boolean accept(File directory, String filename) {
			boolean fileOK = true;

			if (name != null) {
				fileOK &= filename.startsWith(name);
			}

			if (extension != null) {
				fileOK &= filename.endsWith('.' + extension);
			}
			return fileOK;
		}
	}

	public FileDaoQuery() {
		boolean success;
		File storeDir = new File(DB_NAME);
		if (!storeDir.exists()) {
			success = storeDir.mkdirs();
			if (!success)
				return;
		}
		// add files if not present in application area
		/* do not add by default; allow user to request export
		FilenameFilter select = new FileListFilter(filePrefix, fileExtension);

		File[] contents = storeDir.listFiles(select);

		if (contents != null && contents.length == 0) {
			// add new files

			List<BaseChordChart> list = DataAccessUtilities
					.getNewChordChartList();

			for (BaseChordChart c : list) {
				String xml = DataAccessUtilities.getXStream(c);
				String filename = DB_NAME + filePrefix + "-" + c.getChordName()
						+ "-" + c.getChordPosition() + ".xml";

				writeFile(xml, filename);
			}
		}
		*/
	}

	@Override
	public List<String> select(String name, String posit, boolean expand)
			throws SqlJetException {

		List<String> chords = null;

		File storeDir = new File(DB_NAME);
		if (!storeDir.exists()) {
			return chords;
		}

		FilenameFilter select = new FileListFilter(filePrefix, fileExtension);

		File[] contents = storeDir.listFiles(select);

		if (contents != null && contents.length > 0) {
			// filter xml
			String regex = expand ? ".*" : "";

			Pattern p = Pattern.compile(filePrefix + "-" + name + regex + "-"
					+ posit + regex + "." + fileExtension,
					Pattern.CASE_INSENSITIVE);

			chords = new ArrayList<String>();

			for (File file : contents) {
				Matcher m = p.matcher(file.getName());
				if (m.matches()) {
					String xml = readFile(file);
					chords.add(xml);
				}
			}
		}

		return chords;
	}

	@Override
	public int insert(BaseChordChart chart) throws SqlJetException {

		int retVal = 0;

		if (!DataAccessUtilities.validChord(chart))
			return -20;

		chart.setKey(DataAccessUtilities.getKeyId(chart.getChordName(),
				chart.getChordPosition()));
		String xml = DataAccessUtilities.getXStream(chart);
		// save xml to directory
		String filename = DB_NAME + filePrefix + "-" + chart.getChordName()
				+ "-" + chart.getChordPosition() + ".xml";

		File storeDir = new File(DB_NAME);
		if (!storeDir.exists()) {
			if (!storeDir.mkdirs())
				return -25;
		}

		// test for existing
		String filename_test = filePrefix + "-" + chart.getChordName() + "-"
				+ chart.getChordPosition();
		FilenameFilter select = new FileListFilter(filename_test, fileExtension);
		File[] contents = storeDir.listFiles(select);

		if (contents != null && contents.length > 0) {
			// ask for overwrite
			int response;

			response = JOptionPane.showConfirmDialog(null,
					"Should I override the existing file?");

			if (response == JOptionPane.YES_OPTION)
				retVal = writeFile(xml, filename);

		} else {
			retVal = writeFile(xml, filename);
		}
		return retVal;
	}

	/**
	 * write guitariffic file
	 * 
	 * @param retVal
	 * @param xml
	 * @param filename
	 * @return
	 */
	private int writeFile(String xml, String filename) {
		int retVal = 0;
		try {
			System.gc();
			File f = new File(filename);
			if (f.exists())
				f.delete();
			f = null;
			FileWriter writer = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(writer);
			out.write(xml);
			out.close();
			writer.close();
		} catch (IOException e) {
			retVal = -1;
			e.printStackTrace();
		}
		return retVal;
	}

	/**
	 * Read guitariffic file
	 * 
	 * @param file
	 * @return
	 */
	private String readFile(File file) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	@Override
	public int update(BaseChordChart chart) throws SqlJetException {

		int retVal = 0;
		File storeDir = new File(DB_NAME);
		if (!storeDir.exists()) {
			return -1;
		}

		if (!DataAccessUtilities.validChord(chart))
			return -20;

		chart.setKey(DataAccessUtilities.getKeyId(chart.getChordName(),
				chart.getChordPosition()));
		String xml = DataAccessUtilities.getXStream(chart);
		// xml filename
		String filename = DB_NAME + filePrefix + "-" + chart.getChordName()
				+ "-" + chart.getChordPosition() + ".xml";

		// test for existing
		String filename_test = filePrefix + "-" + chart.getChordName() + "-"
				+ chart.getChordPosition() + ".xml";
		FilenameFilter select = new FileListFilter(filename_test, fileExtension);

		File[] contents = storeDir.listFiles(select);

		if (contents.length == 1) {
			int response = JOptionPane.showConfirmDialog(null,
					"Should I override the existing file?");

			if (response == JOptionPane.YES_OPTION)
				retVal = writeFile(xml, filename);
		} else {
			retVal = -10;
		}

		return retVal;
	}

	@Override
	public int delete(BaseChordChart chart) throws SqlJetException {

		int retVal = 0;
		File storeDir = new File(DB_NAME);
		if (!storeDir.exists()) {
			return -1;
		}

		if (!DataAccessUtilities.validChord(chart))
			return -20;

		// test for existing
		String filename_test = filePrefix + "-" + chart.getChordName() + "-"
				+ chart.getChordPosition();
		FilenameFilter select = new FileListFilter(filename_test, fileExtension);

		File[] contents = storeDir.listFiles(select);

		if (contents.length == 1) {
			// delete found file
			for (File file : contents) {
				if (file.exists()) {
					// This ensures under windows that all resources including
					// file resources are released.
					System.gc();
					boolean success = file.delete();
					if (success) {
						retVal = 0;
					} else {
						retVal = -20;
					}
				}
			}
		} else {
			retVal = -10;
		}

		return retVal;
	}

}