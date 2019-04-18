package com.example.mankomania;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mankomania.Network.Client.Client;
import com.example.mankomania.GameData.GameData;
import com.example.mankomania.Network.Server.Server;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Make to run your application only in portrait mode
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Make to run your application only in LANDSCAPE mode
        setContentView(R.layout.activity_main);
    }

    public void btn_Create_Lobby_OnClick(View v) throws Exception {
        EditText et = (EditText) findViewById(R.id.textinput);
        Button btn_create = (Button) findViewById(R.id.btnCreateLobby);
        Button btn_connect = (Button) findViewById(R.id.btnConnectToLobby);

        // Disable Buttons
        btn_create.setEnabled(false);
        btn_connect.setEnabled(false);

        // Set IP Address
        et.setText(getIPAddress());

        // Start Server
        Server server = new Server(1,1000000);
        server.start();

        openMap(et.getText().toString());
    }

    public void btn_Connect_To_Lobby_OnClick(View v)throws Exception{
        EditText et = (EditText) findViewById(R.id.textinput);
        Button btn_create = (Button) findViewById(R.id.btnCreateLobby);
        Button btn_connect = (Button) findViewById(R.id.btnConnectToLobby);

        // Disable Buttons
        btn_create.setEnabled(false);
        btn_connect.setEnabled(false);

        openMap(et.getText().toString());
    }

    public void openMap(String ip){
        // TODO open Map here and Start Client in Map
        Intent intent = new Intent(this,Example.class);
        intent.putExtra("IP",ip);
        startActivity(intent);
    }

    //Get your IPAddress
    private String getIPAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }
}