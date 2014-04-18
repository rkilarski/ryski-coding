/**
 author: Ryszard Kilarski
 email: emrys@bu.edu
 bu id: U81-39-8560
 */
package com.guitariffic.dbo;

/**
 * This class controls the type of storage used by the application.
 */
public class StorageFactory {
    // Current options include "memory" and "sql"
    public static String STORAGE_TYPE = "memory";

    public static GuitarChartDBHelper getGuitarChartDB(String type) {
        if (type.equalsIgnoreCase("memory")) {
            return GuitarChartMemoryDBImpl.getInstance();
        } else if (type.equalsIgnoreCase("sql")) {
            return GuitarChartSqlDBImpl.getInstance();
        }

        return null;
    }

    public static SongDBHelper getSongDB(String type) {
        if (type.equalsIgnoreCase("memory")) {
            return SongMemoryDBImpl.getInstance();
        } else if (type.equalsIgnoreCase("sql")) {
            return SongSqlDBImpl.getInstance();
        }

        return null;
    }
}
