/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.model;

public class Song {
	private String artistName;
	private GuitarChart[][] chords;
	private String id;
	private String[] lyrics;

	private String songName;

	private String[] urls;

	public Song() {
	}

	public Song(String id, String songName, String artistName, String[] lyrics, GuitarChart[][] chords, String[] urls) {
		this.id = id;
		this.songName = songName;
		this.artistName = artistName;
		this.lyrics = lyrics;
		this.chords = chords;
		this.urls = urls;
	}

	/**
	 * Given a guitar chart array, return a deep clone of it.
	 * 
	 * @param src
	 * @return
	 */
	public static GuitarChart[][] cloneChordArray(GuitarChart[][] src) {
		int length = src.length;
		GuitarChart[][] target = new GuitarChart[length][src[0].length];
		for (int i = 0; i < length; i++) {
			GuitarChart[] line = new GuitarChart[src[i].length];
			for (int j = 0; j < src[j].length; j++) {
				line[j] = GuitarChart.newInstance(src[i][j]);
			}
			target[i] = line;
		}
		return target;
	}

	/**
	 * Given a lyrics array, return a deep clone of it.
	 * 
	 * @param src
	 * @return
	 */
	public static String[] cloneStringArray(String[] src) {
		int length = src.length;
		String[] target = new String[length];
		System.arraycopy(src, 0, target, 0, length);
		return target;
	}

	/**
	 * Given a Song, returns a new Song of it.
	 * @param song
	 * @return
	 */
	public static Song newInstance(Song song) {
		return new Song(song.getId(), song.getSongName(), song.getArtistName(), cloneStringArray(song.getLyrics()), cloneChordArray(song.getChords()), null);
	}

	/**
	 * @return the artist
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * @return the chords
	 */
	public GuitarChart[][] getChords() {
		return chords;
	}

	public String getId() {
		return id;
	}

	/**
	 * @return the lyrics
	 */
	public String[] getLyrics() {
		return lyrics;
	}

	/**
	 * @return the name
	 */
	public String getSongName() {
		return songName;
	}

	public String[] getUrls() {
		return urls;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtistName(String artist) {
		this.artistName = artist;
	}

	/**
	 * @param chords
	 *            the chords to set
	 */
	public void setChords(GuitarChart[][] chords) {
		this.chords = chords;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param lyrics
	 *            the lyrics to set
	 */
	public void setLyrics(String[] lyrics) {
		this.lyrics = lyrics;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setSongName(String name) {
		this.songName = name;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	/**
	 * Output the song to the console in a formatted way.
	 * @param song
	 */
	public static void print(Song song) {
		System.out.println("Song: " + song.getSongName());
		System.out.println("Artist: " + song.getArtistName());
		printLyrics(song.getLyrics());
		printCharts(song.getChords());
		System.out.println(" ");
	}

	private static void printLyrics(String[] lyrics) {
		System.out.println("Lyrics");
		printArray(lyrics);
	}

	/**
	 * Print a single-dimensional string array.
	 * @param array
	 */
	private static void printArray(String[] array) {
		if (array == null) {
			return;
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println("     " + array[i]);
		}
	}

	/**
	 * Output charts printed in a grid pattern.
	 */
	private static void printCharts(GuitarChart[][] charts) {
		if (charts == null) {
			return;
		}
		String[] output = new String[charts.length * 4];
		for (int i = 0; i < charts.length; i++) {
			GuitarChart[] row = charts[i];
			output[i * 4] = "";
			output[i * 4 + 1] = "";
			output[i * 4 + 2] = "";
			output[i * 4 + 3] = "";
			for (int j = 0; j < row.length; j++) {
				GuitarChart chart = row[j];
				String name =
						" " + chart.getChordName() + "(" + chart.getChordPosition()
								+ (chart.isLeftHanded() ? "L" : "") + ")";

				output[i * 4] += String.format("%-7s", name);
				output[i * 4 + 1] += " " + chart.getChordFingering();
				output[i * 4 + 2] += " " + chart.getChordFrets();
			}
		}

		// Finally output the data.
		printArray(output);
	}
}
