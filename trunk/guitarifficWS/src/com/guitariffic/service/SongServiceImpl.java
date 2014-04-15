package com.guitariffic.service;

import com.guitariffic.model.Song;
import com.guitariffic.service.exception.SongAlreadyExists;
import com.guitariffic.service.exception.SongNotFound;

public class SongServiceImpl extends BaseService implements SongService {

	@Override
	public String add(Song chart) throws SongAlreadyExists {
		return null;
	}

	@Override
	public String update(Song song, String id) throws SongNotFound {
		return null;
	}

	@Override
	public void delete(String id) throws SongNotFound {
	}

	@Override
	public String[] getList(String search) {
		return null;
	}

	@Override
	public Song get(String id) throws SongNotFound {
		return null;
	}

}
