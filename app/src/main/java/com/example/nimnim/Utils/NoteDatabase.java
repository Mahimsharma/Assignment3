package com.example.nimnim.Utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { NotesEntity.class }, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract com.example.nimnim.Utils.NoteDao getNoteDao();

    private static final String dbName= "NotesDB";
    private static com.example.nimnim.Utils.NoteDatabase noteDB;

    public static com.example.nimnim.Utils.NoteDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static com.example.nimnim.Utils.NoteDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                com.example.nimnim.Utils.NoteDatabase.class,
                dbName)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }

}
