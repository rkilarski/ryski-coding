package com.guitariffic.model;

public class Song {
    private String id;
    private String songName;
    private String artistName;
    private String[] lyrics;

    public Song() {
    }

    public Song(String id, String songName, String artistName, String[] lyrics,
            GuitarChart[][] chords, String[] urls) {
        this.id = id;
        this.songName = songName;
        this.artistName = artistName;
        this.lyrics = lyrics;
        this.chords = chords;
        this.urls = urls;
    }

    /**
     * Given a Song, returns a new Song of it.
     * @param song
     * @return
     */
    public static Song newInstance(Song song) {
        return new Song(song.getId(), song.getSongName(), song.getArtistName(),
                cloneStringArray(song.getLyrics()), cloneChordArray(song.getChords()), null);
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

    private GuitarChart[][] chords;
    private String[] urls;

    /**
     * @return the name
     */
    public String getSongName() {
        return songName;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setSongName(String name) {
        this.songName = name;
    }

    /**
     * @return the artist
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artist
     *            the artist to set
     */
    public void setArtistName(String artist) {
        this.artistName = artist;
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

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}
