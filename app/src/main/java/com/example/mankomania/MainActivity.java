package com.example.mankomania;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mankomania.Network.Client;
import com.example.mankomania.Network.GameData;
import com.example.mankomania.Network.Server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_Create_Lobby (View v) throws Exception {
        EditText et = (EditText) findViewById(R.id.textinput);
        Button btn_create = (Button) findViewById(R.id.btnCreateLobby);
        Button btn_connect = (Button) findViewById(R.id.btnConnectToLobby);

        // Disable Buttons
        btn_create.setEnabled(false);
        btn_connect.setEnabled(false);

        // Get IP Address
        et.setText(getIPAddress());

        // Create game data and set init values
        GameData gameData = generateGameData();

        // Start Server
        Server server = new Server(gameData);
        server.start();

        Client client = new Client("localhost");
        client.start();
    }

    private GameData generateGameData() {
        GameData gameData = new GameData();
        gameData.setPlayer(new String[2]);
        gameData.setMoney(new int[]{1000000,1000000});
        gameData.setPosition(new int[]{0,0});
        return gameData;
    }

    private String getIPAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }
}
