package com.example.nimnim.Utils;




import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("select * from notesentity")
    List<NotesEntity> getAll();


    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insert(NotesEntity note);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(NotesEntity repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(NotesEntity note);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(NotesEntity ...note);      // Note... is varargs, here note is an array

}