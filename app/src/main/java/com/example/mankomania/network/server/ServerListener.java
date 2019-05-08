package com.example.mankomania.network.server;

import android.util.Log;

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
                Thread.sleep(100);
            }
        }catch (Exception e){
            Log.e("SERVER_LISTENER",""+e);
        }
    }
}
