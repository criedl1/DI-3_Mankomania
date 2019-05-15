package com.example.mankomania.network.lobby;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mankomania.MainActivity;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiCastReceiver extends Thread {
    private static String ipaddress = "230.0.0.0";
    private static int port = 6789;
    protected MulticastSocket socket = null;
    private static MainActivity mainActivity;
    private static String name = "MultiCastReceiver";

    @Override
    public void run() {
        try {
            //init
            Log.i(name, "starting");
            socket = new MulticastSocket(port);
            InetAddress group = InetAddress.getByName(ipaddress);
            byte[] buffer = new byte[256];
            socket.joinGroup(group);

            //receive
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            Log.i(name, received);

            //to UI Layer
            publishUpdate(received);

            socket.leaveGroup(group);
            socket.close();
        } catch (Exception e) {
            Log.e(name, "" + e);
        }
    }

    private void publishUpdate(String address){
        Intent intent = new Intent("Lobby.update");
        intent.putExtra("result", address);
        LocalBroadcastManager.getInstance(MultiCastReceiver.mainActivity)
                .sendBroadcast(intent);
    }
}
