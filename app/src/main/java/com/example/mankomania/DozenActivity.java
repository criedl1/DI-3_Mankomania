package com.example.mankomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DozenActivity extends AppCompatActivity {

    RouletteActivity roulette = new RouletteActivity();
    Field[] array = roulette.setUpFields();

    TextView selectDozen;
    Button btn1;
    Button btn13;
    Button btn25;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dozen);

        selectDozen = findViewById(R.id.tvSelectDozen);

        btn1 = findViewById(R.id.btn1_12);
        btn13 = findViewById(R.id.btn13_24);
        btn25 = findViewById(R.id.btn25_36);

        selectDozen.setText("Worauf möchtest du setzen?");
        btn1.setText("1 - 12");
        btn13.setText("13 - 24");
        btn25.setText("25 - 36");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel(1);
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel(2);
            }
        });

        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel(3);
            }
        });
    }

    public String spinWheel(int choosenDozen){

        int money = 1000000; //nur zu Testzwecken

        String returnString = null;
        double randomNumber = roulette.randomNumber();
        int dozen = 0;

        for(int i = 0; i < array.length; i++){
            if(array[i].getValue() == randomNumber){
                if(array[i].getValue() <= 12){
                    dozen = 1;
                }
                else if (array[i].getValue() <= 24 && array[i].getValue() > 12){
                    dozen = 2;
                }
                else {dozen = 3;}
            }
        }

        if (choosenDozen == dozen){
            money = money + 80000; //100000 - 20000 Einsatz
            //ODER muss Kontostand ständig angezeigt werden?
            returnString = "Du hast gewonnen!";}
        else {
            money = money - 20000;
            returnString = "Du hast verloren.";
        }
        return returnString;
    }
}
