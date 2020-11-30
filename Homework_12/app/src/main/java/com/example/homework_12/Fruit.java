package com.example.homework_12;

public class Fruit {
    private String name;
    private String imageId;

    Fruit(String name, String imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getImageId() {
        return imageId;
    }
}
