package com.example.mankomania.Network.Server;

import com.example.mankomania.GameData.GameData;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// Server class
public class Server extends Thread {
    private static GameData gameData;
    private static Queue<String> queue = new LinkedBlockingQueue<>();
    private static Socket[] sockets;
    private static ClientHandler[] clientHandlers;

    public Server(GameData GameData) {
        this.gameData = GameData;
    }

    public void run() {
        try {
            // 0 Player are Connected
            int playerCount = 0;

            // server is listening on port 5056
            ServerSocket serverSocket = new ServerSocket(5056);

            // set arrays for sockets and Handlers
            sockets = new Socket[gameData.getPlayers().length];
            clientHandlers = new ClientHandler[gameData.getPlayers().length];

            //Wait for all Player to connect
            connectPlayers(playerCount, serverSocket);

            //Start the Queue Handler which handles the incoming Messages
            ServerQueueHandler serverQueueHandler = new ServerQueueHandler(clientHandlers,queue);
            serverQueueHandler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectPlayers(int playerCount, ServerSocket serverSocket) throws Exception {
        while (playerCount< gameData.getPlayers().length) {
            // SOCKET object to receive incoming client requests
            sockets[playerCount] = serverSocket.accept();

            // create a new ClientHandler object and start it
            clientHandlers[playerCount] = new ClientHandler(sockets[playerCount],queue,playerCount);
            clientHandlers[playerCount].start();

            // increase countPlayer
            playerCount++;
        }
    }

}