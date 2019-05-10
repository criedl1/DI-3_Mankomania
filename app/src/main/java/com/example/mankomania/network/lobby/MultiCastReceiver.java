package com.example.mankomania.network.lobby;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.mankomania.MainActivity;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiCastReceiver extends Thread {
    private static String IPADDRESS = "230.0.0.0";
    private static int PORT = 666;
    protected MulticastSocket socket = null;
    private static MainActivity mainActivity;

    public MultiCastReceiver(MainActivity mA) {
        mainActivity = mA;
    }

    @Override
    public void run() {
        try {
            //init
            Log.i("MultiCastReceiver", "starting");
            socket = new MulticastSocket(PORT);
            InetAddress group = InetAddress.getByName(IPADDRESS);
            byte[] buffer = new byte[256];
            socket.joinGroup(group);

            //receive
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            Log.i("MultiCastReceiver", received);

            //to UI Layer
            publishUpdate(received);

        } catch (Exception e) {
            Log.e("MultiCastReceiver", "" + e);
        }
    }

    private void publishUpdate(String address){
        Intent intent = new Intent("Lobby.update");
        intent.putExtra("result", address);
        LocalBroadcastManager.getInstance(MultiCastReceiver.mainActivity)
                .sendBroadcast(intent);
    }
}
