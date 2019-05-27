package com.example.mankomania.network.lobby;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mankomania.MainActivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastingClient extends Thread{
    private static DatagramSocket socket = null;
    private static byte[] buf = new byte[256];

    @Override
    public void run() {
        try {
            findLobby("Mankomania.Lobby", InetAddress.getByName("255.255.255.255"));
        } catch (IOException e) {
            Log.e("BroadcastingClient",""+e);
        }
    }

    public void findLobby(String broadcastMessage, InetAddress address) throws IOException {
        broadcast(broadcastMessage, address);

        DatagramPacket received = receiveUDPMessage();

        String res = received.getAddress().toString();
        if(res.startsWith("/"))
            res = res.substring(1);

        publishUpdate(res);
    }

    private static DatagramPacket receiveUDPMessage() throws IOException {
        socket = new DatagramSocket(4446);
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        socket.close();
        return packet;
    }

    private static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);
        socket.send(packet);
        socket.close();
    }

    private void publishUpdate(String address){
        Intent intent = new Intent("Lobby.update");
        intent.putExtra("result", address);
        LocalBroadcastManager.getInstance(new MainActivity())
                .sendBroadcast(intent);
    }
}
