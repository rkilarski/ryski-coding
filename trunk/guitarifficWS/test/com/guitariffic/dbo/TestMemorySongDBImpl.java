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
		Song.print(newSong);
	}

	@Test
	public void testDelete() {
		String id = "3";
		Song chart = dao.get(id);
		assertTrue("No song was returned", chart != null);
		dao.delete(id);

		Song chartDeleted = dao.get(id);
		assertTrue("Song was returned", chartDeleted == null);
	}

	@Test
	public void testGet() {
		String id = "2";
		Song song = dao.get(id);
		assertTrue("No song was returned", song != null);
		Song.print(song);
	}

	@Test
	public void testGetAll() {
		List<Song> list = dao.getList("");
		assertTrue("Missing songs.", list.size() > 0);
		for (Song songItem : list) {
			Song.print(songItem);
		}
	}

	@Test
	public void testGetAllEltonJohnSongs() {
		String EltonJohn = "Elton John";
		List<Song> list = dao.getList(EltonJohn);
		for (Song song : list) {
			assertTrue("This is returning songs other than " + EltonJohn, song.getSongName().toUpperCase().contains(EltonJohn)
					|| song.getArtistName().toUpperCase().contains(EltonJohn.toUpperCase()));
			Song.print(song);
		}
	}

	@Test
	public void testUpdate() {
		String id = "4";
		String newArtist = "Sinead O'Connor";
		Song songFromDB = dao.get(id);
		assertTrue("No song was returned", songFromDB != null);
		Song.print(songFromDB);

		songFromDB.setArtistName(newArtist);
		dao.update(songFromDB, id);

		Song newSong = dao.get(id);
		assertTrue("Fingering wasn't updated!", newSong.getArtistName().equals(newArtist));
		Song.print(newSong);
	}
}
