package com.guitariffic.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.guitariffic.model.Song;
import com.guitariffic.service.exception.SongAlreadyExists;
import com.guitariffic.service.exception.SongNotFound;

public class SongServiceImpl extends BaseService implements SongService {
    private Map<String, Song> map = new HashMap<String, Song>();

    @Override
    public String add(Song song) throws SongAlreadyExists {
        String id = song.getId();
        if (map.get(id) != null) {
            throw new SongAlreadyExists("Cannot add song " + id + ". This song already exists.");
        }
        map.put(id, song);
        return getBaseURL() + "song/" + id;
    }

    @Override
    public String update(Song song, String id) throws SongNotFound {
        Song savedSong = map.get(id);
        if (savedSong == null) {
            throw new SongNotFound("Details of song " + id + " cannot be found.");
        }
        savedSong.setSongName(song.getSongName());
        savedSong.setArtistName(song.getArtistName());
        savedSong.setChords(Song.cloneChordArray(song.getChords()));
        savedSong.setLyrics(Song.cloneStringArray(song.getLyrics()));

        return getBaseURL() + "chart/" + id;
    }

    @Override
    public void delete(String id) throws SongNotFound {
        if (map.get(id) == null) {
            throw new SongNotFound("Details of song " + id + " cannot be found.");
        }
        map.remove(id);
    }

    @Override
    public String[] getList(String search) {
        int size = map.size();
        String[] songs = new String[size];
        Iterator<String> iterator = map.keySet().iterator();
        int i = 0;
        String baseUrl = getBaseURL();
        while (iterator.hasNext()) {
            String chartId = iterator.next();
            songs[i] = baseUrl + "song/" + chartId;
            i++;
        }
        return songs;
    }

    @Override
    public Song get(String id) throws SongNotFound {
        Song song = map.get(id);
        if (song == null) {
            throw new SongNotFound("Details of song " + id + " cannot be found.");
        }
        return song;
    }

}
