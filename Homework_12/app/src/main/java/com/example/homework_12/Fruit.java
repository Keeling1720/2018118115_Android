package com.example.homework_12;

public class Fruit {
    private String name;
    private int imageId;

    Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
