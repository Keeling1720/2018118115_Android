package com.example.nine_patch;

public class Msg {
    public static final int Type_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    private String context;
    private int type;

    public Msg(String context, int type){
        this.context = context;
        this.type = type;
    }
    public String getContext(){
        return context;
    }
    public int getType(){
        return type;
    }
}
