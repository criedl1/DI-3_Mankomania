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
        Log.i("INIT", "Server started with PlayerCount "+ playerCount);
        this.playercount = playerCount;
        this.startmoney = startMoney;
    }

    @Override
    public void run() {
        try (
                // server is listening on port 5056
                ServerSocket serverSocket = new ServerSocket(5056)
                )
        {
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
            Log.e("CLIENT", "" + err);
        }
    }

    private void sendGameData(ServerQueueHandler serverQueueHandler){

        for (int i = 0; i< playercount; i++) {
            serverQueueHandler.sendPlayer(i,gameData.getPlayers()[i]);
            serverQueueHandler.sendMoney(i,gameData.getMoney()[i]);
        }
    }

    private void generateGameData(){
        int[] intArr = new int[playercount];
        int[] intArr2 = new int[playercount];
        boolean[] boolArr = new boolean[playercount];
        String[] strArr = new String[playercount];

        // Set Player[] (fills in ConnectPlayers)
        Arrays.fill(strArr,"");
        gameData.setPlayers(strArr);

        // Set Arrays with StartMoney
        Arrays.fill(intArr2, startmoney);
        gameData.setMoney(intArr2);

        // Set Arrays with 0
        Arrays.fill(intArr,0);
        gameData.setPosition(intArr);
        gameData.setHypoAktie(intArr);
        gameData.setStrabagAktie(intArr);
        gameData.setInfineonAktie(intArr);

        // Set Array with false
        Arrays.fill(boolArr,false);
        gameData.setIsCheater(boolArr);

        // Set Lotto to 0
        gameData.setLotto(0);

        // Set all Hotel to 0
        intArr = new int[5];
        Arrays.fill(intArr,0);
        gameData.setHotels(intArr);
    }

    private void connectPlayers(ServerSocket serverSocket) throws IOException {
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