package com.example.mankomania.Network;

public class GameData {
    public String[] Players;
    public int[] Position;
    public int[] Money;
    public int[] Iron;
    public int[] Oil;
    public boolean[] Cheater;
    public int Hotel_X;
    public int Hotel_Y;
    public int Hotel_z;
    public int Lotto;

    public String[] getPlayers() {
        return Players;
    }
    public void setPlayer(String[] player) {
        Players = player;
    }

    public int[] getPosition() {
        return Position;
    }
    public void setPosition(int[] position) {
        Position = position;
    }

    public int[] getMoney() {
        return Money;
    }
    public void setMoney(int[] money) {
        Money = money;
    }

    public int[] getIron() {
        return Iron;
    }
    public void setIron(int[] iron) {
        Iron = iron;
    }

    public int[] getOil() {
        return Oil;
    }
    public void setOil(int[] oil) {
        Oil = oil;
    }

    public boolean[] getCheater() {
        return Cheater;
    }
    public void setCheater(boolean[] cheater) {
        Cheater = cheater;
    }

    public int getHotel_X() {
        return Hotel_X;
    }
    public void setHotel_X(int hotel_X) {
        Hotel_X = hotel_X;
    }

    public int getHotel_Y() {
        return Hotel_Y;
    }
    public void setHotel_Y(int hotel_Y) {
        Hotel_Y = hotel_Y;
    }

    public int getHotel_z() {
        return Hotel_z;
    }
    public void setHotel_z(int hotel_z) {
        Hotel_z = hotel_z;
    }

    public int getLotto() {
        return Lotto;
    }
    public void setLotto(int lotto) {
        Lotto = lotto;
    }
}
