package com.example.mankomania.Network;

import java.net.ServerSocket;
import java.net.Socket;

// Server class
public class Server extends Thread {
    final private GameData GAMEDATA;

    public Server(GameData GameData) {
        this.GAMEDATA = GameData;
    }

    public void run() {
        try {
            // server is listening on port 5056
            ServerSocket serverSocket = new ServerSocket(5056);
            int playerCount = 0;
            Socket[] sockets = new Socket[GAMEDATA.getPlayers().length];

            while (playerCount<GAMEDATA.getPlayers().length) {
                // SOCKET object to receive incoming client requests
                sockets[playerCount] = serverSocket.accept();
                System.out.println("A new client is connected : " + sockets[playerCount]);

                // create a new thread object
                Thread t = new ClientHandler(sockets[playerCount]);
                // Start Thread
                t.start();

                // increase countPlayer
                playerCount++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}