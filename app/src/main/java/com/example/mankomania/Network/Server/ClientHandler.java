package com.example.mankomania.Network.Server;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

class ClientHandler extends Thread {
    private final Socket SOCKET;
    private final PrintWriter OUTPUT;
    private final BufferedReader INPUT;
    private Queue<String[]> queue;
    private int id;
    private int playerCount;

    public ClientHandler(Socket socket, Queue queue, int id, int playerCount) throws Exception{
        this.SOCKET = socket;
        this.OUTPUT = new PrintWriter(new BufferedWriter(new OutputStreamWriter(SOCKET.getOutputStream())), true);
        this.INPUT = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        this.queue = queue;
        this.id = id;
        this.playerCount = playerCount;
    }

    @Override
    public void run() {
        try {
            // start ServerListener for incoming Messages
            ServerListener serverListener = new ServerListener(INPUT,queue);
            sendID();
            sendPlayerCount();
            serverListener.start();

            sendID();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendID() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("OPERATION", "SET_ID");
        jsonObject.addProperty("ID",id);
        send(jsonObject.toString());
    }
    private void sendPlayerCount() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("OPERATION", "SET_PLAYER_COUNT");
        jsonObject.addProperty("COUNT",playerCount);
        send(jsonObject.toString());
    }

    public void send(String string){
        OUTPUT.println(string);
    }
}