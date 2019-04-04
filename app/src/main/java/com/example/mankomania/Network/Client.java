package com.example.mankomania.Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

// Client class
public class Client
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket socket = new Socket(ip, 5056);

            // obtaining input and out
            PrintWriter output =  new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
