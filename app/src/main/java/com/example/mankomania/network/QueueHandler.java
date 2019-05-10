package com.example.mankomania.network;

import android.util.Log;

import java.util.Queue;

public abstract class QueueHandler extends Thread {
    protected Queue<String> queue;

    @Override
    public void run() {
        String in;
        try {
            while (true) {
                // wait for a new message in the queue
                if (!queue.isEmpty()) {
                    in = queue.poll();
                    handleMessage(in);
                }
            }
        } catch (Exception e) {
            Log.e("QUEUE_HANDLER", "" + e);
        }
    }

    protected abstract void handleMessage(String in);
}
