package com.example.mankomania.network.server;

import android.util.Log;

import java.io.BufferedReader;
import java.util.Queue;

public class ServerListener extends Thread {
    private BufferedReader in;
    private Queue queue;

    ServerListener(BufferedReader in, Queue queue){
        this.in = in;
        this.queue = queue;
    }

    @Override
    public void run(){
        try{
            // reading Messages and adding them to the queue
            while(true){
                String msg = in.readLine();
                if(msg != null){
                    queue.offer(msg);
                }
            }
        }catch (Exception e){
            Log.e("SERVER_LISTENER",""+e);
        }
    }
}
