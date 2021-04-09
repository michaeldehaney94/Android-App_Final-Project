package com.example.baseproject;

public class PostImage extends Post {

    private String imageUrl;

    public PostImage() {
        super.setType("image");
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
