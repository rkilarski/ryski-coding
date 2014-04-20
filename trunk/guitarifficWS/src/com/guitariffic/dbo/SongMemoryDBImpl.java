/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.dbo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.guitariffic.model.GuitarChart;
import com.guitariffic.model.Song;

public class SongMemoryDBImpl implements SongDBHelper {
	private static SongDBHelper instance;
	private static Map<String, Song> map = null;

	public SongMemoryDBImpl() {
		if (map == null) {
			map = loadFromMemory();
		}
	}

	private Map<String, Song> loadFromMemory() {
		Map<String, Song> map = new HashMap<String, Song>();
		Song song = null;

		song = new Song();
		song.setArtistName("Elton John");
		song.setSongName("Your Song");
		String[] yourSong = { "It's a little bit funny", "This feeling inside" };
		song.setLyrics(yourSong);
		song.setId("1");
		map.put("1", song);

		song = new Song();
		song.setArtistName("Elton John");
		song.setSongName("I Guess That's Why They Call It The Blues");
		song.setId("2");
		map.put("2", song);

		song = new Song();
		song.setArtistName("Elton John");
		song.setSongName("Can You Feel The Love Tonight");
		song.setId("3");
		map.put("3", song);

		song = new Song();
		song.setArtistName("Elton John");
		song.setSongName("Sacrifice");
		String[] sacrifice = { "It's a human sign", "When things go wrong" };
		song.setLyrics(sacrifice);

		GuitarChart[][] chords = new GuitarChart[2][2];
		chords[0][0] = new GuitarChart("1", "A", "1", "123456", "654321", false);
		chords[0][1] = new GuitarChart("2", "B", "2", "234567", "765432", true);
		chords[1][0] = new GuitarChart("3", "C", "3", "345678", "876543", false);
		chords[1][1] = new GuitarChart("4", "D", "4", "456789", "987654", true);
		song.setChords(chords);
		song.setId("4");
		map.put("4", song);

		song = new Song();
		song.setArtistName("Eric Clapton");
		song.setSongName("Layla");
		String[] layla =
				{ "What'll you do when you get lonely", "And nobody's waiting by your side?",
						"You've been running and hiding much too long.",
						"You know it's just your foolish pride" };
		song.setLyrics(layla);
		song.setId("5");
		map.put("5", song);

		return map;
	}

	public static SongDBHelper getInstance() {
		if (instance == null) {
			instance = new SongMemoryDBImpl();
		}
		return instance;
	}

	@Override
	public String add(Song song) {
		String id = Integer.toString(map.size() + 1);
		song.setId(id);
		map.put(id, song);
		return id;
	}

	@Override
	public void delete(String id) {
		map.remove(id);
	}

	@Override
	public Song get(String id) {
		return map.get(id);
	}

	@Override
	public List<Song> getList(String search) {
		if (search == null) {
			search = "";
		}
		List<Song> list = new ArrayList<Song>();
		Iterator<Entry<String, Song>> it = map.entrySet().iterator();
		String searchUpper = search.toUpperCase();
		while (it.hasNext()) {
			Entry<String, Song> pairs = it.next();
			String upperSongName = pairs.getValue().getSongName().toUpperCase();
			String upperArtistName = pairs.getValue().getArtistName().toUpperCase();

			// Either get all, or find all matches on song name or artist name.
			if (search.equals("") || search.isEmpty() || upperSongName.contains(searchUpper)
					|| upperArtistName.contains(searchUpper)) {
				list.add(pairs.getValue());
			}
		}
		return list;
	}

	@Override
	public void update(Song song, String id) {
		map.put(id, song);
	}
}
