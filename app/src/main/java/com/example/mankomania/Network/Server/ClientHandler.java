package com.example.mankomania.Network.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

// ClientHandler class
class ClientHandler extends Thread {
    final private Socket SOCKET;


    // Constructor
    public ClientHandler(Socket socket) {
        this.SOCKET = socket;
    }

    @Override
    public void run() {

        try {
            // obtaining INPUT and out streams
            PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(SOCKET.getOutputStream())), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));



            // Say Hello to Client
            output.println("Hello");

            // receive the answer from client
            String in = input.readLine();

            // Print Answer
            System.out.println("in = " + in);

            // closing resources
            input.close();
            output.close();
            SOCKET.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}