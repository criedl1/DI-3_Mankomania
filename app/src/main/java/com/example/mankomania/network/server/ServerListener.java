package com.example.mankomania.network.server;

import java.io.BufferedReader;
import java.util.Queue;

public class ServerListener extends Thread {
    private BufferedReader in;
    private Queue<String> queue;

    public ServerListener(BufferedReader in, Queue queue){
        this.in = in;
        this.queue = queue;
    }

    @Override
    public void run(){
        try{
            // reading Messages and adding them to the queue
            while(true){
                queue.offer(in.readLine());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
