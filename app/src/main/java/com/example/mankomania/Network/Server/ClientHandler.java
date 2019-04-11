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

    public ClientHandler(Socket socket, Queue queue, int id) throws Exception{
        this.SOCKET = socket;
        this.OUTPUT = new PrintWriter(new BufferedWriter(new OutputStreamWriter(SOCKET.getOutputStream())), true);
        this.INPUT = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            // start ServerListener for incoming Messages
            ServerListener serverListener = new ServerListener(INPUT,queue);
            sendID();
            serverListener.start();

            // Send ID to Client
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

    public void send(String string){
        OUTPUT.println(string);
    }
}