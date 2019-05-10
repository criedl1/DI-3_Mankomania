package com.example.mankomania.network.server;

import android.util.Log;

import com.example.mankomania.gamedata.GameData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// Server class
public class Server extends Thread {
    private static GameData gameData = new GameData();
    private static Queue<String> queue = new LinkedBlockingQueue<>();
    private Socket[] sockets;
    private ClientHandler[] clientHandlers;
    private final int playercount;
    private final int startmoney;

    public Server(int playerCount, int startMoney) {
        Log.i("INITJS", "Server started with PlayerCount "+ playerCount);
        this.playercount = playerCount;
        this.startmoney = startMoney;
    }

    @Override
    public void run() {
        try
        {
            // server is listening on port 5056
            ServerSocket serverSocket = new ServerSocket(5056);

            // set arrays for sockets and Handlers
            sockets = new Socket[playercount];
            clientHandlers = new ClientHandler[playercount];

            //generate GameData
            generateGameData();

            //Wait for all Player to connect
            connectPlayers(serverSocket);

            //Queue Handler which handles the incoming Messages
            ServerQueueHandler serverQueueHandler = new ServerQueueHandler(clientHandlers,queue,gameData);

            // Send GameData to all
            sendGameData(serverQueueHandler);

            // Start with Player 0
            serverQueueHandler.startTurn(0);

            // Start listening
            serverQueueHandler.start();

            //Close Socket
            serverQueueHandler.join();
            joinClientHandlers(clientHandlers);
            closeSockets(sockets);
        } catch (Exception err) {
            Log.e("CLIENT", "" + err);
        }
    }

    private void closeSockets(Socket[] sockets) throws IOException{
        for (Socket socket: sockets) {
            socket.close();
        }
    }

    private void joinClientHandlers(ClientHandler[] clientHandlers) throws InterruptedException{
        for (ClientHandler clientHandler: clientHandlers) {
            clientHandler.join();
        }
    }

    private void sendGameData(ServerQueueHandler serverQueueHandler){

        for (int i = 0; i< playercount; i++) {
            serverQueueHandler.sendPlayer(i,gameData.getPlayers()[i]);
            serverQueueHandler.sendMoney(i,gameData.getMoney()[i]);
        }
    }

    private void generateGameData(){
        gameData.initEmptyGameData(playercount);

        // Set Arrays with StartMoney
        int[] intArr = new int[playercount];
        Arrays.fill(intArr, startmoney);
        gameData.setMoney(intArr);
    }

    private void connectPlayers(ServerSocket serverSocket) throws IOException, InterruptedException {
        int playerCount = 0;
        String[] arr;

        while (playerCount< gameData.getPlayers().length) {
            // socket object to receive incoming client requests
            sockets[playerCount] = serverSocket.accept();

            // Set player address in Players[]
            arr =gameData.getPlayers();
            arr[playerCount] = sockets[playerCount].getInetAddress().getHostAddress();
            gameData.setPlayers(arr);
            Log.i("JONTEST","Connected Player "+sockets[playerCount].getInetAddress().toString()+" ("+playerCount+")");
            // create a new ClientHandler object and start it
            clientHandlers[playerCount] = new ClientHandler(sockets[playerCount],queue,playerCount, playercount);
            clientHandlers[playerCount].start();
            clientHandlers[playerCount].sendPlayerCount();
            clientHandlers[playerCount].sendID();

            // increase countPlayer
            playerCount++;
        }
    }

}