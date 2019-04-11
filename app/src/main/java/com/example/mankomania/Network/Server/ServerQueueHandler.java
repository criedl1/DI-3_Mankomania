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

    private void sendAllClients(String in) {
        //send Command to all Clients
        for (ClientHandler client : clientHandlers) {
            client.send(in+" back");
        }
    }
}
