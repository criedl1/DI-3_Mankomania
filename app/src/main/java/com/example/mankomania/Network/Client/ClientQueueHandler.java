package com.example.mankomania.Network.Client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Queue;

public class ClientQueueHandler extends Thread{
    private Queue<String> queue;
    Client client;

    public ClientQueueHandler(Queue<String> queue, Client client) {
        this.queue = queue;
        this.client = client;
    }

    public void run(){
        String in;
        try{
            while (true){
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

        switch (jsonObject.get("OPERATION").getAsString()) {
            // set ID of the Client
            case "SET_ID":
                client.setId(jsonObject.get("ID").getAsString());
                break;
            default:
                break;
        }
        System.out.println("jsonObject = " + jsonObject);
    }

    public void toUpperLayer(String in){
        // TODO
        System.out.println(in);
    }
}
