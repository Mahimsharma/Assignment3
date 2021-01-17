package com.example.nimnim.Utils;

public class ImageEntity {

    private int image;
    private String imageUrl;
    private String imageTitle;


    public ImageEntity(int image){
        this.image=image;
    }
    public ImageEntity(String imageUrl,String imageTitle)
    {
        this.imageUrl=imageUrl;
        this.imageTitle=imageTitle;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageCreator() {
        return imageTitle;
    }

    public void setImageCreator(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
