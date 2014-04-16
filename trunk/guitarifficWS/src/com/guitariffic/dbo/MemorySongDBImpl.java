package com.guitariffic.dbo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.guitariffic.model.Song;

public class MemorySongDBImpl implements DBHelper<Song> {
	private Map<String, Song> map = new HashMap<String, Song>();

	@Override
	public void add(Song object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Song object, String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Song> getList(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Song get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
