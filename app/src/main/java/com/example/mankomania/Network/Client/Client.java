package com.example.mankomania.Network.Client;

import com.example.mankomania.GameData.GameData;
import com.example.mankomania.Map.start_view;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

// Client class
public class Client extends Thread {
    private final GameData gameData = new GameData();
    private static String ipHost;
    private static PrintWriter output;
    private static BufferedReader input;
    private Queue<String> queue;
    private int idx;
    public static com.example.mankomania.Map.start_view start_view;

    public Client(String ipHost, com.example.mankomania.Map.start_view start_view){
        this.ipHost = ipHost;
        this.start_view = start_view;
    }

    public void run() {
        try {
            queue = new LinkedBlockingQueue<>();

            // getting localhost ip
            InetAddress ip = InetAddress.getByName(ipHost);

            // establish the connection with server port 5056
            Socket socket = new Socket(ip, 5056);

            // obtaining INPUT and out
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // start ClientListener for incoming Messages
            ClientListener clientListener = new ClientListener(input,queue);
            clientListener.start();

            // start CLienQueueHandler
            ClientQueueHandler clientQueueHandler = new ClientQueueHandler(queue,this, gameData);
            clientQueueHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Index
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public int getIdx() {
        return idx;
    }

    //Server Requests
    public void setMoneyOnServer(final int idx, final int money){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","sendMoney");
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
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","setPosition");
                json.addProperty("PLAYER", idx);
                json.addProperty("Position", pos);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setHypoAktieOnServer(final int idx,final int count){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","setHypoAktie");
                json.addProperty("PLAYER", idx);
                json.addProperty("Count", count);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setStrabagAktieOnServer(final int idx,final int count){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","setStrabagAktie");
                json.addProperty("PLAYER", idx);
                json.addProperty("Count", count);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setInfineonAktieOnServer(final int idx, final int count){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","setInfineonAktie");
                json.addProperty("PLAYER", idx);
                json.addProperty("Count", count);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setCheaterOnServer(final int idx,final boolean cheater){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","setCheater");
                json.addProperty("PLAYER", idx);
                json.addProperty("Cheater", cheater);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setLottoOnServer(final int amount){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","setLotto");
                json.addProperty("Amount", amount);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void setHotelOnServer(final int idx,final int owner){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","setHotel");
                json.addProperty("Hotel", idx);
                json.addProperty("Owner", owner);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void rollTheDice(){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","rollDice");
                json.addProperty("Player",idx);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void spinTheWheel(){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","spinWheel");
                json.addProperty("Player",idx);
                output.println(json.toString());
            }
        };
        thread.start();
    }
    public void endTurn(){
        // new Thread because Network cant be on the UI Thread (temp Fix)
        Thread thread = new Thread(){
            public void run(){
                JsonObject json = new JsonObject();
                json.addProperty("OPERATION","endTurn");
                json.addProperty("Player",idx);
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
