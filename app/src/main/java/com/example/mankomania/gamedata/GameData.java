package com.example.mankomania.gamedata;

import android.util.Log;

import com.example.mankomania.network.server.ServerQueueHandler;

import java.util.Arrays;

public class GameData {
    private String[] IPAdresses;
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

    private int[] isCheater;
    private boolean[] didBlame;

    private int hasTurn = 0;
    private ServerQueueHandler server;

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

    public String[] getIPAdresses() {
        return IPAdresses.clone();
    }

    public void setIPAdresses(String[] player) {
        IPAdresses = player;
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
        for (String player : this.IPAdresses) {
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

    public int[] getIsCheater() {

        return isCheater.clone();
    }

    public void setIsCheater(int[] isCheater) {
        this.isCheater = isCheater;
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
        int[] boolArr = new int[playerCount];
        String[] strArr = new String[playerCount];
        boolean[] blameArr = new boolean[playerCount];
        boolean[] didBlame = new boolean[playerCount];
        String[] namesArr = new String[playerCount];

        // Set Player[] (fills in ConnectPlayers)
        Arrays.fill(strArr, "");
        setIPAdresses(strArr);

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

        // Set Array with -1
        Arrays.fill(boolArr, -1);
        setIsCheater(boolArr);


        Arrays.fill(blameArr, false);
        setDidBlame(blameArr);

        // Set Lotto to 0
        setLotto(0);

        // Set all Hotel to 0
        intArr = new int[5];
        Arrays.fill(intArr, 0);
        setHotels(intArr);
    }

    public boolean[] getDidBlame() {
        return didBlame;
    }

    public void setDidBlame(boolean[] didBlame) {
        this.didBlame = didBlame;
    }

    public void decrementCheater() {
        int[] cheaterArr = this.getIsCheater();
        int index = 0;
        for (int i : this.isCheater) {
            if (i > 0) {
                i--;
                if(i==0){
                    server.sendDidCheatSuccessfully(index);
                }
                cheaterArr[index] = i;
            }
            index++;
        }
        setIsCheater(cheaterArr);
    }

    public void setServerPlayer(int player) {
        this.playerServer = player;
    }

    synchronized public void setName(int idx, String name) {
        this.names[idx] = name;
    }
}
