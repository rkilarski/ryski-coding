package com.guitariffic.dbo;

/**
 * This class controls the type of storage used by the application.
 * 
 */
public class StorageFactory {
    // Current options include "memory".
    public static String STORAGE_TYPE = "memory";

    public static SongDBHelper getSongDB(String type) {
        if (type.equalsIgnoreCase("memory")) {
            return MemorySongDBImpl.getInstance();
        }
        return null;
    }

    public static GuitarChartDBHelper getGuitarChartDB(String type) {
        if (type.equalsIgnoreCase("memory")) {
            return MemoryGuitarChartDBImpl.getInstance();
        }
        return null;
    }
}
