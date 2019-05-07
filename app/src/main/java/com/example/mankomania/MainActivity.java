package com.example.mankomania;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mankomania.network.server.Server;
import com.example.mankomania.map.MapView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int selectedPlayerCount = 1;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.playerCount);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.player_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);
    }

    public void btn_Create_Lobby_OnClick(View v) {
        Button btnCreate =  findViewById(R.id.btnCreateLobby);
        Button btnConnect =  findViewById(R.id.btnConnectToLobby);

        // Disable Buttons
        btnCreate.setEnabled(false);
        btnConnect.setEnabled(false);

        this.ip = getIPAddress();

        // Set ip Address
        Toast.makeText(this, getString(R.string.display_server_ip, ip), Toast.LENGTH_LONG).show();

        // Start Server
        Server server = new Server(selectedPlayerCount, 1000000);
        server.start();

        openMap();
    }

    public void btn_Connect_To_Lobby_OnClick(View v)  {
        Log.i("INIT", "Clicked");
        EditText et =  findViewById(R.id.textinput);
        Button btnCreate =  findViewById(R.id.btnCreateLobby);
        Button btnConnect =  findViewById(R.id.btnConnectToLobby);

        // Disable Buttons
        btnCreate.setEnabled(false);
        btnConnect.setEnabled(false);

        this.ip = et.getText().toString();
        openMap();
    }

    public void openMap() {
        Log.i("INIT", "Open Map with "+ ip);
        Intent intent = new Intent(this, MapView.class);
        intent.putExtra("ip", ip);
        startActivity(intent);
    }

    //Get your IPAddress
    private String getIPAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i("INIT", "Selection changed to: "+ position);
        this.selectedPlayerCount = position+1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //is not possible at this time
    }
}