package com.example.mankomania.Network.Client;

import com.example.mankomania.GameData.GameData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

// Client class
public class Client extends Thread {
    private static GameData gameData = new GameData();
    private static String ipHost;
    private static PrintWriter output;
    private static BufferedReader input;
    private Queue<String> queue;

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
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // start ClientListener for incoming Messages
            ClientListener clientListener = new ClientListener(input,queue);
            clientListener.start();

            // start CLienQueueHandler
            ClientQueueHandler clientQueueHandler = new ClientQueueHandler(queue);
            clientQueueHandler.start();

            //For Testing
            setMoney(0,0);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMoney(int player, int money) {
        //TODO
        output.println("Hello from CLient");
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
