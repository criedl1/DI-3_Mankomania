package com.example.mankomania.network.lobby;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MultiCastPublisher extends Thread {
    private static String ipaddress = "230.0.0.0";
    private static int port = 6789;
    private String message;

    public MultiCastPublisher(String msg){
        message = msg;
    }

    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket()){
            InetAddress group = InetAddress.getByName(ipaddress);

            //send via Multi-cast
            byte[] buf = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
            socket.send(packet);

            socket.close();
            Log.i("MultiCastPublisher", message);
        }catch (Exception e){
            Log.e("MultiCastPublisher",""+e);
        }
    }
}
