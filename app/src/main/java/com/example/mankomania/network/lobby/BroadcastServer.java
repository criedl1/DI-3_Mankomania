package com.example.mankomania.network.lobby;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastServer extends Thread {
    private byte[] buf = new byte[256];

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(4445)){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            InetAddress address = packet.getAddress();
            packet = new DatagramPacket(buf, buf.length, address, 4446);

            Thread.sleep(200);

            socket.send(packet);
        }catch (Exception e){
            Log.e("BroadcastServer",""+e);
        }
    }
}