package com.example.mankomania.network.server;

import android.util.Log;

import com.example.mankomania.gamedata.GameData;

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
        Log.i("INIT", "Server started with PlayerCount "+ playerCount);
        this.playercount = playerCount;
        this.startmoney = startMoney;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            // server is listening on port 5056
            serverSocket = new ServerSocket(5056);

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
            clientHandlers[0].giveTurn();

            // Start listening
            serverQueueHandler.start();

        } catch (Exception err) {
            Log.e("CLIENT", ""+ err);
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (Exception e) {
                    Log.e("CLIENT", ""+ e);
                }
            }
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (Exception e) {
                    Log.e("CLIENT", ""+ e);
                }
            }
        }
    }

    private void sendGameData(ServerQueueHandler serverQueueHandler){

        for (int i = 0; i< playercount; i++) {
            serverQueueHandler.sendPlayer(i,gameData.getPlayers()[i]);
            serverQueueHandler.sendMoney(i,gameData.getMoney()[i]);
        }
    }

    private void generateGameData(){
        int[] int_arr = new int[playercount];
        int[] int_arr2 = new int[playercount];
        boolean[] bool_arr = new boolean[playercount];
        String[] str_arr = new String[playercount];

        // Set Player[] (fills in ConnectPlayers)
        Arrays.fill(str_arr,"");
        gameData.setPlayers(str_arr);

        // Set Arrays with StartMoney
        Arrays.fill(int_arr2, startmoney);
        gameData.setMoney(int_arr2);

        // Set Arrays with 0
        Arrays.fill(int_arr,0);
        gameData.setPosition(int_arr);
        gameData.setHypoAktie(int_arr);
        gameData.setStrabagAktie(int_arr);
        gameData.setInfineonAktie(int_arr);

        // Set Array with false
        Arrays.fill(bool_arr,false);
        gameData.setIsCheater(bool_arr);

        // Set Lotto to 0
        gameData.setLotto(0);

        // Set all Hotel to 0
        int_arr = new int[5];
        Arrays.fill(int_arr,0);
        gameData.setHotels(int_arr);
    }

    private void connectPlayers(ServerSocket serverSocket) throws Exception {
        int playerCount = 0;
        String[] arr;

        while (playerCount< gameData.getPlayers().length) {
            // SOCKET object to receive incoming client requests
            sockets[playerCount] = serverSocket.accept();

            // Set player address in Players[]
            arr =gameData.getPlayers();
            arr[playerCount] = sockets[playerCount].getInetAddress().getHostAddress();
            gameData.setPlayers(arr);

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