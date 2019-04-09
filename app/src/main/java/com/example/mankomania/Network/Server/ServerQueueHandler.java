package com.example.mankomania.Network.Server;

import java.util.Queue;

public class ServerQueueHandler extends Thread{
    ClientHandler[] clientHandlers;
    private Queue<String> queue;

    public ServerQueueHandler(ClientHandler[] clientHandlers, Queue queue){
        this.queue = queue;
        this.clientHandlers = clientHandlers;
    }

    public void run(){
        //Maybe ThreadPool for Speed up
        String in;
        try{
            while(true){
                // wait for a new message in the queue
                if(!queue.isEmpty()){
                    in = queue.poll();

                    //TODO Handle Message

                    // send Command
                    sendAllClients(in);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendAllClients(String in) {
        //send Command to all Clients
        for (ClientHandler client : clientHandlers) {
            client.send(in+" back");
        }
    }
}
