package com.example.mankomania.network.lobby;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastServer extends Thread {
    private DatagramSocket socket;
    private byte[] buf = new byte[256];

    public void run() {
        try {
            socket = new DatagramSocket(4445);
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, 4446);

            Thread.sleep(200);

            socket.send(packet);

            socket.close();
        }catch (Exception e){
            Log.e("BroadcastServer",""+e);
        }
    }
}