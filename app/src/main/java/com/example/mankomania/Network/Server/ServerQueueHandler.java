package com.example.mankomania.Network.Server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Queue;

public class ServerQueueHandler extends Thread{
    ClientHandler[] clientHandlers;
    private Queue<String> queue;


    public ServerQueueHandler(ClientHandler[] clientHandlers, Queue queue){
        this.queue = queue;
        this.clientHandlers = clientHandlers;
    }

    public void run(){
        String in;
        try{
            while(true){
                // wait for a new message in the queue
                if(!queue.isEmpty()){
                    in = queue.poll();
                    handleMessage(in);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleMessage(String in) {
        // TODO Handle Message
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(in).getAsJsonObject();
    }

    public void sendAllClients(String message) {
        //send Command to all Clients
        for (ClientHandler client : clientHandlers) {
            client.send(message);
        }
    }

    public void setPlayer(int idx, String str){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setPlayer");
        json.addProperty("PLAYER", idx);
        json.addProperty("IP", str);
        sendAllClients(json.toString());
    }

    public void setMoney(int idx, int money){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setMoney");
        json.addProperty("PLAYER", idx);
        json.addProperty("Money", money);
        sendAllClients(json.toString());
    }

    public void setPosition(int idx, int pos){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setPosition");
        json.addProperty("PLAYER", idx);
        json.addProperty("Position", pos);
        sendAllClients(json.toString());
    }

    public void setHypoAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setHypoAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        sendAllClients(json.toString());
    }

    public void setStrabagAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setStrabagAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        sendAllClients(json.toString());
    }

    public void setInfineonAktie(int idx, int count){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setInfineonAktie");
        json.addProperty("PLAYER", idx);
        json.addProperty("Count", count);
        sendAllClients(json.toString());
    }

    public void setCheater(int idx, boolean cheater){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setCheater");
        json.addProperty("PLAYER", idx);
        json.addProperty("Cheater", cheater);
        sendAllClients(json.toString());
    }

    public void setLotto(int amount){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setLotto");
        json.addProperty("Amount", amount);
        sendAllClients(json.toString());
    }

    public void setHotel(int idx, int owner){
        JsonObject json = new JsonObject();
        json.addProperty("OPERATION","setHotel");
        json.addProperty("Hotel", idx);
        json.addProperty("Owner", owner);
        sendAllClients(json.toString());
    }
}
