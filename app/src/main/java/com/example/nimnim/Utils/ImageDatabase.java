package com.example.nimnim.Utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { ImageCache.class }, version = 1)

public abstract class ImageDatabase extends RoomDatabase {

    public abstract ImageDao getImageDao();
   

    private static final String dbName= "ImageDB";
    private static ImageDatabase imageDB;

    public static ImageDatabase getInstance(Context context) {
        if (null == imageDB) {
            imageDB = buildDatabaseInstance(context);
        }
        return imageDB;
    }

    private static ImageDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                ImageDatabase.class,
                dbName)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        imageDB = null;
    }

}
