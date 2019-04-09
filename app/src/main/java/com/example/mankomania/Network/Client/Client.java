package com.example.mankomania.Network.Client;

import com.example.mankomania.GameData.GameData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

// Client class
public class Client extends Thread {
    final protected GameData GAMEDATA = new GameData();
    private String ipHost;

    public Client(String ipHost){
        this.ipHost = ipHost;
    }

    public void run() {
        try {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName(ipHost);

            // establish the connection with server port 5056
            Socket socket = new Socket(ip, 5056);

            // obtaining INPUT and out
            PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //read Server message
            String in = input.readLine();
            System.out.println("in = " + in);

            //Answer Server
            output.println(in + " back");

            // closing resources
            scn.close();
            output.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMoney(int player, int money) {
        //TODO
    }

    public int getMoney(int player) {
        //TODO
        return -1;
    }

    public void setPosition(int player, int money) {
        //TODO
    }

    public int getPosition(int player) {
        //TODO
        return -1;
    }

}
