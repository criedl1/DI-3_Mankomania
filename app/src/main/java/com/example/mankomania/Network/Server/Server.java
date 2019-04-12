package com.example.mankomania.Network.Server;

import com.example.mankomania.GameData.GameData;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

// Server class
public class Server extends Thread {
    private static GameData gameData = new GameData();
    private static Queue<String> queue = new LinkedBlockingQueue<>();
    private static Socket[] sockets;
    private static ClientHandler[] clientHandlers;
    private final int PLAYERCOUNT;
    private final int STARTMONEY;

    public Server(int playerCount, int startMoney) {
        this.PLAYERCOUNT = playerCount;
        this.STARTMONEY = startMoney;
    }

    public void run() {
        try {
            // server is listening on port 5056
            ServerSocket serverSocket = new ServerSocket(5056);

            // set arrays for sockets and Handlers
            sockets = new Socket[PLAYERCOUNT];
            clientHandlers = new ClientHandler[PLAYERCOUNT];

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendGameData(ServerQueueHandler serverQueueHandler){

        for (int i=0; i<PLAYERCOUNT;i++) {
            serverQueueHandler.sendPlayer(i,gameData.getPlayers()[i]);
            serverQueueHandler.sendMoney(i,gameData.getMoney()[i]);
        }
    }

    private void generateGameData(){
        int[] int_arr = new int[PLAYERCOUNT];
        int[] int_arr2 = new int[PLAYERCOUNT];
        boolean[] bool_arr = new boolean[PLAYERCOUNT];
        String[] str_arr = new String[PLAYERCOUNT];

        // Set Player[] (fills in ConnectPlayers)
        Arrays.fill(str_arr,"");
        gameData.setPlayers(str_arr);

        // Set Arrays with StartMoney
        Arrays.fill(int_arr2,STARTMONEY);
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
            clientHandlers[playerCount] = new ClientHandler(sockets[playerCount],queue,playerCount,PLAYERCOUNT);
            clientHandlers[playerCount].start();
            clientHandlers[playerCount].sendPlayerCount();
            clientHandlers[playerCount].sendID();

            // increase countPlayer
            playerCount++;
        }
    }

}