package com.example.mankomania.Network.Client;

import com.example.mankomania.GameData.GameData;
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
    public final GameData gameData = new GameData();
    private static String ipHost;
    private static PrintWriter output;
    private static BufferedReader input;
    private Queue<String> queue;
    private int idx;

    public Client(String ipHost){
        this.ipHost = ipHost;
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

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setMoneyOnServer(int idx, int money){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","sendMoney");
        json.addProperty("PLAYER", idx);
        json.addProperty("Money", money);
        output.println(json.toString());
    }

    public void setPostionOnServer(int idx, int pos){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setPosition");
        json.addProperty("PLAYER", idx);
        json.addProperty("Position", pos);
        output.println(json.toString());
    }

    public void setHypoAktieOnServer(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setHypoAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        output.println(json.toString());
    }

    public void setStrabagAktieOnServer(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setStrabagAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        output.println(json.toString());
    }

    public void setInfineonAktieOnServer(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setInfineonAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        output.println(json.toString());
    }

    public void setCheaterOnServer(int idx, boolean cheater){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setCheater");
        json.addProperty("PLAYER", idx);
        json.addProperty("Cheater", cheater);
        output.println(json.toString());
    }

    public void setLottoOnServer(int amount){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setLotto");
        json.addProperty("Amount", amount);
        output.println(json.toString());
    }

    public void setHotelOnServer(int idx, int owner){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setHotel");
        json.addProperty("Hotel", idx);
        json.addProperty("Owner", owner);
        output.println(json.toString());
    }

    public String getOwnIP(){
        return gameData.getPlayers()[idx];
    }

    public void rollTheDice(){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","rollDice");
        json.addProperty("Player",idx);
        output.println(json.toString());
    }

    public void spinTheWheel(){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","spinWheel");
        json.addProperty("Player",idx);
        output.println(json.toString());
    }

    public void endTurn(){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","endTurn");
        json.addProperty("Player",idx);
        output.println(json.toString());
    }
}
