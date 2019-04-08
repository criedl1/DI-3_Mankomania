package com.example.mankomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumberActivity extends AppCompatActivity {

    RouletteClass roulette = new RouletteClass();
    EditText number;
    TextView selectNumber;
    Button go;
    int choosenNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        number = findViewById(R.id.etInsertNumber);
        selectNumber = findViewById(R.id.tvSelectNumber);
        go = findViewById(R.id.btnGo);

        selectNumber.setText("Auf welche Zahl möchtest du setzen?");
        number.setHint("Zahl eingeben");
        go.setText("Setzen!");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //was ist wenn EditText leer?
                choosenNumber = Integer.valueOf(number.getText().toString());
                spinWheel(choosenNumber);
            }
        });

    }

    public String spinWheel(int choosenNumber) {

        int money = 1000000; //nur zu Testzwecken

        double rouletteNumber = roulette.spinIt();
        String returnString = null;

        if (rouletteNumber == choosenNumber) {
            money = money + 145000;  //--> 150000 - 5000 Einsatz
            //ODER muss Kontostand ständig angezeigt werden?
            returnString = "Du hast gewonnen!";
        } else {
            money = money - 50000; //Einsatz
            returnString = "Du hast verloren.";
        }
        return returnString;
    }
    }

