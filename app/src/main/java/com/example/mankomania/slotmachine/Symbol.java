package com.example.mankomania.slotmachine;

public class Symbol {
    int image;
    int id;

    protected Symbol(int image, int id){
        this.id = id;
        this.image = image;
    }

    protected int getImage(){
        return this.image;
    }

    protected void setImage(int image){
        this.image = image;
    }

    protected int getId(){
        return this.id;
    }
}
