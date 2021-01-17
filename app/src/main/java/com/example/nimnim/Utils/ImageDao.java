package com.example.nimnim.Utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImageDao {

    @Query("select * from imagecache")
    List<ImageCache> getAll();


    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insert(ImageCache imageCache);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(ImageCache repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(ImageCache imageCache);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(List<ImageCache> imageCache);      // Note... is varargs, here note is an array

}
