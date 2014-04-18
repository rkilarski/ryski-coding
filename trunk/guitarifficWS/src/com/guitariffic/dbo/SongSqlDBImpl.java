/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.dbo;

import java.util.List;

import com.guitariffic.model.Song;

/**
 * Future functionality to use Sql database.
 */

public class SongSqlDBImpl implements SongDBHelper {

    private static SongDBHelper instance;

    public static SongDBHelper getInstance() {
        if (instance == null) {
            instance = new SongMemoryDBImpl();
        }
        return instance;
    }

    @Override
    public String add(Song object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Song get(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Song> getList(String search) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Song object, String id) {
        // TODO Auto-generated method stub

    }

}
