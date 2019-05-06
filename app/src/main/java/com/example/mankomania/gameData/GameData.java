package com.example.mankomania.gameData;

import com.example.mankomania.network.server.ServerQueueHandler;
import com.example.mankomania.map.Map;

public class GameData {
    private String[] players;
    private int[] position;
    private int[] money;
    private int lotto;
    private int[] hotels;
    private int[] infineonAktie;
    private int[] hypoAktie;
    private int[] strabagAktie;
    private boolean[] isCheater;
    private int hasTurn = 0;
    private Map map;
    private ServerQueueHandler server;

    public GameData() {
        this.map = new Map();
    }

    public String[] getPlayers() {
        return players.clone();
    }
    public void setPlayers(String[] player) {
        players = player;
    }

    public int[] getPosition() {
        return position.clone();
    }
    public void setPosition(int[] position) {
        this.position = position;
    }

    //sets position of specific player and sends changes to all clients
    public void setPosition(int player, int position){
        this.getPosition()[player] = position;
        this.server.sendPosition(player,position);
        // do the action (here because if he gets moved further we have a new position)
        this.map.getField(position).doAction(this);
    }

    public int[] getMoney() {
        return money.clone();
    }
    public void setMoney(int[] money) {
        this.money = money;
    }

    //sets money of a player and sends change to all clients
    public void setMoney(int player, int money){
        this.getMoney()[player] = money;
        if(this.server != null) {
            this.server.sendMoney(player, money);
        }
    }

    public int getPlayerCount(){
        int count = 0;
        for (String player : this.players) {
            if(player != null){
                count++;
            }
        }
        return count;
    }

    public int getLotto() {
        return lotto;
    }

    //sets lotto money and sends changes to all Clients
    public void setLotto(int lotto) {
        this.lotto = lotto;
        if(this.server != null){
            this.server.sendLotto(lotto);
        }
    }

    public int[] getHotels() {
        return hotels.clone();
    }
    public void setHotels(int[] hotels) {
        this.hotels = hotels;
    }

    public int[] getInfineonAktie() {
        return infineonAktie.clone();
    }
    public void setInfineonAktie(int[] infineonAktie) {
        this.infineonAktie = infineonAktie;
    }

    public int[] getHypoAktie() {
        return hypoAktie.clone();
    }
    public void setHypoAktie(int[] hypoAktie) {
        this.hypoAktie = hypoAktie;
    }

    public int[] getStrabagAktie() {
        return strabagAktie.clone();
    }
    public void setStrabagAktie(int[] strabagAktie) {
        this.strabagAktie = strabagAktie;
    }

    public boolean[] getIsCheater() {
        return isCheater.clone();
    }
    public void setIsCheater(boolean[] isCheater) {
        this.isCheater = isCheater;
    }

    public void setTurn(int player) {
        this.hasTurn = player;
    }

    public int getHasTurn() {
        return hasTurn;
    }

    //moves Player x fields further (trigering all actions on the way) and on the last field
    public void movePlayer(int result) {
        int currentPosition = this.getPosition()[this.hasTurn];
        for(int i = 0; i < result; i++){
            currentPosition = (currentPosition+1)%this.map.getMapSize();
            this.map.getField(currentPosition).goOver(this);
        }
        currentPosition = (currentPosition+1)%this.map.getMapSize();
        this.setPosition(this.getHasTurn(),currentPosition);
    }

    public void setServer(ServerQueueHandler serverQueueHandler) {
        this.server = serverQueueHandler;
    }
}
