package com.example.mankomania.Network.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

class ClientHandler extends Thread {
    final private Socket SOCKET;
    private final PrintWriter OUTPUT;
    private final BufferedReader INPUT;
    private Queue<String[]> queue;

    public ClientHandler(Socket socket, Queue queue) throws Exception{
        this.SOCKET = socket;
        this.OUTPUT = new PrintWriter(new BufferedWriter(new OutputStreamWriter(SOCKET.getOutputStream())), true);
        this.INPUT = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            // Say Hello to Client
            send("Hello");

            // start ServerListener for incoming Messages
            ServerListener serverListener = new ServerListener(INPUT,queue);
            serverListener.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String string){
        OUTPUT.println(string);
    }
}