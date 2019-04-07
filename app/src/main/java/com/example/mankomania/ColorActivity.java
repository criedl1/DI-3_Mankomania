package com.example.mankomania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ColorActivity extends AppCompatActivity {

    RouletteActivity roulette = new RouletteActivity();
    Field[] array = roulette.setUpFields();

    Button red;
    Button black;
    TextView selectColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);


        red = findViewById(R.id.btnRed);
        black = findViewById(R.id.btnBlack);
        selectColor = findViewById(R.id.tvSelectColor);

        red.setText("Rot");
        black.setText("Schwarz");
        selectColor.setText("Auf welche Farbe möchtest du setzen?");

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel(enum_color.RED);
                openRotateActivity();
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel(enum_color.BLACK);
                openRotateActivity();
            }
        });
    }

    public String spinWheel(enum_color choosenColor){

        int money = 1000000; //nur zu Testzwecken

        double randomNumber = roulette.randomNumber();
        String returnString = null;

        for(int i = 0; i < array.length; i++){
            if(randomNumber == array[i].getValue()){
                if(array[i].getColor() == choosenColor){
                    money = money + 30000;  //--> 80000-50000 Einsatz
                    //ODER muss Kontostand ständig angezeigt werden?
                    returnString = "Du hast gewonnen!";
                }
                else{
                    money = money - 5000; //Einsatz
                    returnString = "Du hast verloren.";
                }
            }
        }
        return returnString;
    }
    public void openRotateActivity(){
        Intent it = new Intent(this, RotateActivity.class);
        startActivity(it);
    }
}
