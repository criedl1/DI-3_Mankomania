package com.example.mankomania.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

// ClientHandler class
class ClientHandler extends Thread {
    final BufferedReader input;
    final PrintWriter output;
    final Socket socket;


    // Constructor
    public ClientHandler(Socket socket, BufferedReader input, PrintWriter output) {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {

        try {

            // Say Hello to Client
            output.println("Hello");

            // receive the answer from client
            String in = input.readLine();

            //Print Answer
            System.out.println("in = " + in);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            // closing resources
            this.input.close();
            this.output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}