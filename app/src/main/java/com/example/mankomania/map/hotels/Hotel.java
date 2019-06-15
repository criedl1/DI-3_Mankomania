package com.example.mankomania.map.hotels;

import com.example.mankomania.map.Player;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String hotelName;
    private Player owner;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.owner = null;
    }

    public String getHotelName() {
        return hotelName;
    }

    public boolean isSold() {
        return owner != null;
    }

    public boolean setOwner(Player owner) {
        if (this.owner == null) {
            this.owner = owner;
            return true;
        }
        return false;
    }

    public Player getOwner() {
        return owner;
    }
}
