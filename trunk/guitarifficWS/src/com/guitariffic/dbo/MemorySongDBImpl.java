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

public class MemorySongDBImpl implements SongDBHelper {
    private static Map<String, Song> map = null;
    private static SongDBHelper instance;

    public MemorySongDBImpl() {
        if (map == null) {
            map = new HashMap<String, Song>();
        }
    }

    public static SongDBHelper getInstance() {
        if (instance == null) {
            instance = new MemorySongDBImpl();
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
    public void update(Song song, String id) {
        map.put(id, song);
    }

    @Override
    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public List<Song> getList(String search) {
        List<Song> list = new ArrayList<Song>();
        Iterator<Entry<String, Song>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Song> pairs = it.next();
            list.add(pairs.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        return list;
    }

    @Override
    public Song get(String id) {
        return map.get(id);
    }
}
