package com.example.mankomania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDice = findViewById(R.id.btndice);

    }
    public void onClick(View view){
        Intent intent = new Intent(this,dice.class);
        startActivity(intent);
    }

}
