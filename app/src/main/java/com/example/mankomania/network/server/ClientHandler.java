package com.example.mankomania.network.server;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import com.example.mankomania.network.NetworkConstants;

class ClientHandler extends Thread {
    private final PrintWriter output;
    private final BufferedReader input;
    private Queue<String[]> queue;
    private int id;
    private int playerCount;

    ClientHandler(Socket socket, Queue queue, int id, int playerCount) throws Exception{
        this.output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.queue = queue;
        this.id = id;
        this.playerCount = playerCount;
    }

    @Override
    public void run() {
        try {
            // start ServerListener for incoming Messages
            ServerListener serverListener = new ServerListener(input,queue);
            serverListener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void giveTurn() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("OPERATION",NetworkConstants.START_TURN);
        jsonObject.addProperty("Player", id);
        //Send only one Player
        send(jsonObject.toString());
    }

    void sendID() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("OPERATION", NetworkConstants.SET_ID);
        jsonObject.addProperty("ID",id);
        send(jsonObject.toString());
    }

    void sendPlayerCount() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("OPERATION", NetworkConstants.SET_PLAYER_COUNT);
        jsonObject.addProperty("COUNT",playerCount);
        send(jsonObject.toString());
    }

    void send(String string){
        output.println(string);
    }
}