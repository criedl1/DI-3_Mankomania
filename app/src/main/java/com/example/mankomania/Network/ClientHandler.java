package com.example.mankomania.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

// ClientHandler class
class ClientHandler extends Thread {
    final protected BufferedReader INPUT;
    final protected PrintWriter OUTPUT;
    final protected Socket SOCKET;


    // Constructor
    public ClientHandler(Socket socket, BufferedReader input, PrintWriter output) {
        this.SOCKET = socket;
        this.INPUT = input;
        this.OUTPUT = output;
    }

    @Override
    public void run() {

        try {

            // Say Hello to Client
            OUTPUT.println("Hello");

            // receive the answer from client
            String in = INPUT.readLine();

            //Print Answer
            System.out.println("in = " + in);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            // closing resources
            this.INPUT.close();
            this.OUTPUT.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}