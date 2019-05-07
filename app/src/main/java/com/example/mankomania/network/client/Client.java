package com.example.mankomania.network.client;

import android.util.Log;

import com.example.mankomania.gamedata.GameData;
import com.example.mankomania.map.MapView;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import com.example.mankomania.network.NetworkConstants;

// Client class
public class Client extends Thread {
    private final GameData gameData = new GameData();
    private static String ipHost;
    private PrintWriter output;
    private int idx;
    public static MapView mapView;

    public Client(){
    }

    public void init(String ipHost, MapView mapView){
        Client.ipHost = ipHost;
        Client.mapView = mapView;
    }

    @Override
    public void run() {
        try (
                // establish the connection with server port 5056
                Socket socket = new Socket(InetAddress.getByName(ipHost), 5056);
                // obtaining INPUT and out
                PrintWriter output1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                )
        {
            Queue<String> queue = new LinkedBlockingQueue<>();

            output= output1;

            // start ClientListener for incoming Messages
            ClientListener clientListener = new ClientListener(input, queue);
            clientListener.start();

            // start CLienQueueHandler
            ClientQueueHandler clientQueueHandler = new ClientQueueHandler(queue, this, gameData);
            clientQueueHandler.start();
        } catch (Exception err) {
            Log.e("CLIENT", ""+ err);
        }
    }

    //Index
    void setIdx(int idx) {
        this.idx = idx;
    }
    public int getIdx() {
        return idx;
    }

    //Server Requests
    public void setMoneyOnServer(final int idx, final int money){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION",NetworkConstants.SEND_MONEY);
                json.addProperty("PLAYER", idx);
                json.addProperty("Money", money);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setPostionOnServer(final int idx,final int pos){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_POSITION);
                json.addProperty(NetworkConstants.PLAYER, idx);
                json.addProperty(NetworkConstants.POSITION, pos);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setHypoAktieOnServer(final int idx,final int count){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_HYPO_AKTIE);
                json.addProperty(NetworkConstants.PLAYER, idx);
                json.addProperty(NetworkConstants.COUNT, count);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setStrabagAktieOnServer(final int idx,final int count){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_STRABAG_AKTIE);
                json.addProperty(NetworkConstants.PLAYER, idx);
                json.addProperty(NetworkConstants.COUNT, count);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setInfineonAktieOnServer(final int idx, final int count){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_INFINEON_AKTIE);
                json.addProperty(NetworkConstants.PLAYER, idx);
                json.addProperty(NetworkConstants.COUNT, count);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setCheaterOnServer(final int idx,final boolean cheater){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_CHEATER);
                json.addProperty(NetworkConstants.PLAYER, idx);
                json.addProperty(NetworkConstants.CHEATER, cheater);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setLottoOnServer(final int amount){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_LOTTO);
                json.addProperty(NetworkConstants.AMOUNT, amount);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setHotelOnServer(final int idx,final int owner){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SET_HOTEL);
                json.addProperty(NetworkConstants.HOTEL, idx);
                json.addProperty(NetworkConstants.OWNER, owner);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void rollTheDice(){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Log.i("DICEEX","Clientside");
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.ROLL_DICE);
                json.addProperty(NetworkConstants.PLAYER,idx);
                output.println(json.toString());
            }
        };
        thread.start();
    }

    public void playRoulette(){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.SPIN_WHEEL);
                json.addProperty(NetworkConstants.PLAYER,idx);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void endTurn(){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            @Override
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty(NetworkConstants.OPERATION,NetworkConstants.END_TURN);
                json.addProperty(NetworkConstants.PLAYER,idx);
                output.println(json.toString());
            }
        };
        thread.start();
    }

    //GameDate Requests
    public String getOwnIP(){
        return gameData.getPlayers()[idx];
    }
    public String[] getPlayers() {
        return gameData.getPlayers();
    }
    public int[] getPosition() {
        return gameData.getPosition();
    }
    public int[] getMoney() {
        return gameData.getMoney();
    }
    public int getLotto() {
        return gameData.getLotto();
    }
    public int[] getHotels() {
        return gameData.getHotels();
    }
    public int[] getInfineonAktie() {
        return gameData.getInfineonAktie();
    }
    public int[] getHypoAktie() {
        return gameData.getHypoAktie();
    }
    public int[] getStrabagAktie() {
        return gameData.getStrabagAktie();
    }
    public boolean[] getIsCheater() {
        return gameData.getIsCheater();
    }

}
