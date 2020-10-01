package com.example.fourthhomework;

import android.widget.ImageView;

public class Car {
    private String name;
    private int imageId;

    public Car(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
