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

	public String add(Song object);

	public void delete(String id);

	public Song get(String id);

	public List<Song> getList(String search);

	public void update(Song object, String id);
}
