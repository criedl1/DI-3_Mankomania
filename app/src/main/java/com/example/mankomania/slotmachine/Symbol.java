package com.example.mankomania.slotmachine;

import android.widget.ImageView;

public class Symbol {
    ImageView image;
    int id;

    protected Symbol(ImageView iv, int id){
        this.id = id;
        this.image = iv;
    }

    protected ImageView getImage(){
        return this.image;
    }

    protected int getId(){
        return this.id;
    }
}
