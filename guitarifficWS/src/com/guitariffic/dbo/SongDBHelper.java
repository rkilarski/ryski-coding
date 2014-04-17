/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.dbo;

import java.util.List;

import com.guitariffic.model.Song;

/**
 * Interface to expose database functionality for songs.
 *
 */
public interface SongDBHelper {

	public void add(Song object);

	public void update(Song object, String id);

	public void delete(String id);

	public List<Song> getList(String search);

	public Song get(String id);
}
