package com.example.mankomania.network.client;

import android.util.Log;

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
                String msg = in.readLine();
                Log.i("JONTEST", "Listener: "+msg);
                queue.offer(msg);

            }
        }catch (Exception e){
            Log.e("CLIENT_LISTENER",""+e);
        }
    }
}
