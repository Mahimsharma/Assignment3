package com.example.nimnim.Utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { NotesEntity.class }, version = 1)

public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();

    private static final String dbName= "NotesDB";
    private static NoteDatabase noteDB;

    public static NoteDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static NoteDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                NoteDatabase.class,
                dbName)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }

}
