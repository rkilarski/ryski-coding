package com.guitariffic.model;

public class Song {
	private String id;
	private String name;
	private String artist;
	private String[] lyrics;
	private GuitarChart[][] chords;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the lyrics
	 */
	public String[] getLyrics() {
		return lyrics;
	}

	/**
	 * @param lyrics
	 *            the lyrics to set
	 */
	public void setLyrics(String[] lyrics) {
		this.lyrics = lyrics;
	}

	/**
	 * @return the chords
	 */
	public GuitarChart[][] getChords() {
		return chords;
	}

	/**
	 * @param chords
	 *            the chords to set
	 */
	public void setChords(GuitarChart[][] chords) {
		this.chords = chords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
