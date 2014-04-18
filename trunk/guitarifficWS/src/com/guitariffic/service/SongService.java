/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.service;

import com.guitariffic.model.Song;
import com.guitariffic.service.exception.SongAlreadyExists;
import com.guitariffic.service.exception.SongNotFound;

public interface SongService {
	public abstract String add(Song chart) throws SongAlreadyExists;

	public abstract void delete(String id) throws SongNotFound;

	public abstract Song get(String id) throws SongNotFound;

	public abstract String[] getList(String search);

	public abstract String update(Song song, String id) throws SongNotFound;

}