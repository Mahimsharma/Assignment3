package com.example.nimnim.Utils;
import android.graphics.Bitmap;
import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImageCache {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

//    private Image image;
//
//
//    public Image getImage() {
//        return image;
//    }
//
//    public void setImage(Image image) {
//        this.image = image;
//    }

}
