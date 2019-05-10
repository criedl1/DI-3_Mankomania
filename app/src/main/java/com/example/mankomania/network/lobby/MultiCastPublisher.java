package com.example.mankomania.network.lobby;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MultiCastPublisher extends Thread {
    private static String IPADDRESS = "230.0.0.0";
    private static int PORT = 6789;
    private String message;

    public MultiCastPublisher(String msg){
        message = msg;
    }

    @Override
    public void run() {
        try {
            //init
            DatagramSocket socket = new DatagramSocket();
            InetAddress group = InetAddress.getByName(IPADDRESS);
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] receiveData = new byte[1024];

            //send via Multi-cast
            byte[] buf = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, PORT);
            socket.send(packet);

            Log.i("MultiCastPublisher", message);
        }catch (Exception e){
            Log.e("MultiCastPublisher",""+e);
        }
    }
}
