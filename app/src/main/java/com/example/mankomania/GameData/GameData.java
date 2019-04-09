package com.example.mankomania.GameData;

public class GameData {
    private String[] Players;
    private int[] Position;
    private int[] Money;
    private int Lotto;

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
}
