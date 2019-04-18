package com.example.mankomania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.mankomania.Network.Client.Client;


public class Example extends AppCompatActivity {
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        //Get Intent to get the IP and start client
        Intent intent = getIntent();
        client = new Client(intent.getStringExtra("IP"));
        client.start();
    }

    public void btn_Display_IP_OnClick(View v) throws Exception {
        EditText et = (EditText) findViewById(R.id.editText);

        // change something
        client.setPostionOnServer(0, 23);

        Thread.sleep(1);

        // Get something
        et.setText("Position: " + client.gameData.getPosition()[0]);
    }
}
