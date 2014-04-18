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

import com.guitariffic.model.Song;

public class SongMemoryDBImpl implements SongDBHelper {
    private static SongDBHelper instance;
    private static Map<String, Song> map = null;

    public SongMemoryDBImpl() {
        if (map == null) {
            map = new HashMap<String, Song>();
        }
    }

    public static SongDBHelper getInstance() {
        if (instance == null) {
            instance = new SongMemoryDBImpl();
            map = new HashMap<String, Song>();
        }
        return instance;
    }

    @Override
    public String add(Song song) {
        String id = Integer.toString(map.size() + 1);
        song.setId(id);
        map.put(song.getId(), song);
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

            //Either get all, or find all matches on song name or artist name.
            if (search.equals("") || search.isEmpty() || upperSongName.contains(searchUpper)
                    || (upperArtistName.contains(searchUpper))) {
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
