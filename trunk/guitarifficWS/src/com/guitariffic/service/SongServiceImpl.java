/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.service;

import java.util.List;

import com.guitariffic.dbo.SongDBHelper;
import com.guitariffic.dbo.StorageFactory;
import com.guitariffic.image.ImageImpl;
import com.guitariffic.model.Song;
import com.guitariffic.service.exception.SongAlreadyExists;
import com.guitariffic.service.exception.SongNotFound;

/**
 * Implementation class for the song service. This class supports
 * basic/add/edit/delete/get/list operations for songs.
 */

public class SongServiceImpl extends BaseService implements SongService {
	private static SongDBHelper dao = null;

	public SongServiceImpl() {
		if (dao == null) {
			dao = StorageFactory.getSongDB(StorageFactory.STORAGE_TYPE);
		}
	}

	@Override
	public String add(Song song) throws SongAlreadyExists {
		String id = song.getId();
		if (dao.get(id) != null) {
			throw new SongAlreadyExists("Cannot add song " + id + ". This song already exists.");
		}
		dao.add(song);
		return getBaseURL() + "song/" + id;
	}

	@Override
	public String update(Song song, String id) throws SongNotFound {
		Song savedSong = (Song) dao.get(id);
		if (savedSong == null) {
			throw new SongNotFound("Details of song " + id + " cannot be found.");
		}
		savedSong.setSongName(song.getSongName());
		savedSong.setArtistName(song.getArtistName());
		savedSong.setChords(Song.cloneChordArray(song.getChords()));
		savedSong.setLyrics(Song.cloneStringArray(song.getLyrics()));
		dao.update(savedSong, id);

		return getBaseURL() + "song/" + id;
	}

	@Override
	public void delete(String id) throws SongNotFound {
		if (dao.get(id) == null) {
			throw new SongNotFound("Details of song " + id + " cannot be found.");
		}
		dao.delete(id);
	}

	@Override
	public String[] getList(String search) {
		List<Song> songs = dao.getList(search);
		int size = songs.size();
		String[] songsArray = new String[size];
		int i = 0;
		String baseUrl = getBaseURL();
		for (Song song : songs) {
			String id = song.getId();
			songsArray[i] = baseUrl + "song/" + id;
			i++;
		}
		return songsArray;
	}

	@Override
	public Song get(String id) throws SongNotFound {
		Song song = dao.get(id);
		if (song == null) {
			throw new SongNotFound("Details of song " + id + " cannot be found.");
		}

		// Call image service to get list of image URLs.
		ImageImpl imageService = ImageImpl.newImageImpl("flickr");
		List<String> imageList = imageService.getImages(song.getArtistName());
		String[] urlList = new String[imageList.size()];
		int i = 0;
		for (String url : imageList) {
			urlList[i] = url;
			i++;
		}
		song.setUrls(urlList);
		return song;
	}

}
