package com.example.mankomania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.mankomania.Network.Client.Client;
import com.google.gson.Gson;


public class Example extends AppCompatActivity {
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        //Get Intent and start client
        Intent intent = getIntent();
        client = new Client(intent.getStringExtra("IP"));
        client.start();
    }
}
