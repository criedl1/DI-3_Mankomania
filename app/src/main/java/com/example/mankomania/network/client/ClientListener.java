package com.example.mankomania.network.client;

import java.io.BufferedReader;
import java.util.Queue;

public class ClientListener extends Thread {
    private BufferedReader in;
    private Queue<String> queue;

    public ClientListener(BufferedReader in, Queue queue) {
        this.in = in;
        this.queue = queue;
    }

    @Override
    public void run(){
        try{
            while (true){
                // reading Messages and adding them to the queue
                queue.offer(in.readLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
