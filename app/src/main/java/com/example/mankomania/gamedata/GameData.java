package com.example.mankomania.gamedata;

import android.util.Log;

import com.example.mankomania.network.server.ServerQueueHandler;

import java.util.Arrays;

public class GameData {
    private String[] ipAdresses;
    private int[] position;
    private int[] money;
    private int lotto;
    private int[] hotels;
    private int[] infineonAktie;
    private int[] hypoAktie;
    private int[] strabagAktie;

    private int[] sandwirthHotel;
    private int[] plattenwirtHotel;
    private int[] seeparkHotel;


    private int hasTurn = 0;
    private ServerQueueHandler server;
    private int[] order;

    public int getPlayerServer() throws InterruptedException {
        while(playerServer == -1){
            Thread.sleep(100);
            Log.d("ORDER","Waiting for someone to tell me who is server");
        }
        return playerServer;
    }

    private int playerServer = -1;

    public String[] getNames() {
        return names.clone();
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    private String[] names;

    public String[] getIpAdresses() {
        return ipAdresses.clone();
    }

    public void setIpAdresses(String[] player) {
        ipAdresses = player;
    }

    public int[] getPosition() {
        return position.clone();
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    //sets position of specific player and sends changes to all clients
    public void setPosition(int player, int position) {
        this.position[player] = position;
    }

    public int[] getMoney() {
        return money.clone();
    }

    public void setMoney(int[] money) {
        this.money = money;
    }

    //sets money of a player and sends change to all clients
    public void setMoney(int player, int money) {
        this.getMoney()[player] = money;
        if (this.server != null) {
            this.server.sendMoney(player, money);
        }
    }

    public int getPlayerCount() {
        int count = 0;
        for (String player : this.ipAdresses) {
            if (player != null) {
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
        if (this.server != null) {
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


    public int[] getSandwirthHotel() {
        return sandwirthHotel.clone();
    }
    public void setSandwirthHotel(int[] sandwirthHotel) {
        this.sandwirthHotel = sandwirthHotel;
    }
    public int[] getPlattenwirtHotel() {
        return plattenwirtHotel.clone();
    }
    public void setPlattenwirtHotel(int[] plattenwirtHotel) {
        this.plattenwirtHotel = plattenwirtHotel;
    }
    public int[] getSeeparkHotel() {
        return seeparkHotel.clone();
    }
    public void setSeeparkHotel(int[] seeparkHotel) {
        this.seeparkHotel = seeparkHotel;
    }



    public void setTurn(int player) {
        this.hasTurn = player;
    }

    public int getHasTurn() {
        return hasTurn;
    }

    public void setServer(ServerQueueHandler serverQueueHandler) {
        this.server = serverQueueHandler;
    }

    public void initEmptyGameData(int playerCount) {
        int[] intArr = new int[playerCount];
        String[] strArr = new String[playerCount];
        String[] namesArr = new String[playerCount];

        // Set Player[] (fills in ConnectPlayers)
        Arrays.fill(strArr, "");
        setIpAdresses(strArr);

        // set Player Names
        Arrays.fill(namesArr,null);
        setNames(namesArr);


        // Set Arrays with 0
        Arrays.fill(intArr, 0);
        setMoney(intArr);
        setPosition(intArr);
        setHypoAktie(intArr);
        setStrabagAktie(intArr);
        setInfineonAktie(intArr);

        // Set Lotto to 0
        setLotto(0);

        // Set all Hotel to 0
        intArr = new int[5];
        Arrays.fill(intArr, 0);
        setHotels(intArr);
    }



    public void setServerPlayer(int player) {
        this.playerServer = player;
    }

    public synchronized void setName(int idx, String name) {
        this.names[idx] = name;
    }

    public void setOrder(int[] order) {
        this.order = order;
    }

    public int[] getOrder() {
        return this.order;
    }

    public int getPlayerIndex(int player) {
        for (int i = 0; i < this.order.length; i++) {
            if(this.order[i]==player){
                return i;
            }
        }
        return -1;
    }
}
