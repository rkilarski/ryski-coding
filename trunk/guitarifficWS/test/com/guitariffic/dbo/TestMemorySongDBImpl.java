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
import com.guitariffic.model.Song;

/**
 * JUnit to test out the song memory API.
 */
public class TestMemorySongDBImpl {
    private static SongDBHelper dao = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao = StorageFactory.getSongDB(StorageFactory.STORAGE_TYPE);
    }

    @Test
    public void testAdd() {
        Song song = new Song();
        song.setArtistName("Elton John");
        song.setSongName("Crocodile Rock");

        String id = dao.add(song);
        assertTrue("No id was returned", Integer.parseInt(id) > 0);

        Song newSong = dao.get(id);
        assertTrue("No song was returned", newSong != null);
        printSong(newSong);
    }

    @Test
    public void testDelete() {
        Song song = new Song();
        song.setArtistName("Elton John");
        song.setSongName("I Guess That's Why They Call It The Blues");

        String id = dao.add(song);
        assertTrue("No id was returned", Integer.parseInt(id) > 0);

        Song chart = dao.get(id);
        assertTrue("No song was returned", chart != null);
        dao.delete(id);

        Song chartDeleted = dao.get(id);
        assertTrue("Song was returned", chartDeleted == null);
    }

    @Test
    public void testGet() {
        Song song = new Song();
        song.setArtistName("Elton John");
        song.setSongName("Can You Feel The Love Tonight");

        String id = dao.add(song);
        assertTrue("No id was returned", Integer.parseInt(id) > 0);

        song = dao.get(id);
        assertTrue("No song was returned", song != null);
        printSong(song);
    }

    @Test
    public void testGetAll() {
        Song song = new Song();
        song.setArtistName("Elton John");
        song.setSongName("Your SOng");

        String id = dao.add(song);
        assertTrue("No id was returned", Integer.parseInt(id) > 0);

        List<Song> list = dao.getList("");
        assertTrue("Missing songs.", list.size() > 0);
        for (Song songItem : list) {
            printSong(songItem);
        }
    }

    @Test
    public void testGetAllEltonJohnSongs() {
        String EltonJohn = "ELTON JOHN";
        List<Song> list = dao.getList(EltonJohn);
        for (Song song : list) {
            assertTrue("This is returning songs other than " + EltonJohn, song.getSongName()
                    .toUpperCase().contains(EltonJohn)
                    || song.getArtistName().toUpperCase().contains(EltonJohn));
            printSong(song);
        }
    }

    @Test
    public void testUpdate() {
        Song song = new Song();
        song.setArtistName("Elton John");
        song.setSongName("Sacrifice");
        String id = dao.add(song);

        String newArtist = "Sinead O'Connor";
        Song songFromDB = dao.get(id);
        assertTrue("No song was returned", songFromDB != null);
        printSong(songFromDB);

        songFromDB.setArtistName(newArtist);
        dao.update(songFromDB, id);

        Song newSong = dao.get(id);
        assertTrue("Fingering wasn't updated!", newSong.getArtistName().equals(newArtist));
        printSong(newSong);
    }

    private static void printSong(Song song) {
        System.out.println("Song: " + song.getSongName());
        System.out.println("Artist: " + song.getArtistName());
        printLyrics(song.getLyrics());
        printCharts(song.getChords());
        System.out.println(" ");
    }

    private static void printLyrics(String[] lyrics) {
        printArray(lyrics);
    }

    private static void printArray(String[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

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
                output[i * 4] +=
                        " " + chart.getChordName() + "(" + chart.getChordPosition()
                                + chart.isLeftHanded() + ")";
                output[i * 4 + 1] += " " + chart.getChordFingering();
                output[i * 4 + 2] += " " + chart.getChordFrets();
            }
        }

        // Finally output the data.
        printArray(output);
    }

}
