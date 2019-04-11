package com.example.mankomania.Network.Client;

import java.util.Queue;

public class ClientQueueHandler extends Thread{
    private Queue<String> queue;


    public ClientQueueHandler(Queue<String> queue) {
        this.queue = queue;
    }

    public void run(){
        String in;
        try{
            while (true){
                // wait for a new message in the queue
                if(!queue.isEmpty()){
                    in = queue.poll();

                    // TODO Handle Message

                    toUpperLayer(in);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void toUpperLayer(String in){
        // TODO
        System.out.println(in);
    }
}
