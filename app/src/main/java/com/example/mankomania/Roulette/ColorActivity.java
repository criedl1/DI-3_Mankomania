package com.example.mankomania.Roulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mankomania.R;

public class ColorActivity extends AppCompatActivity {

    RouletteClass roulette = new RouletteClass();
    FieldClass[] array = roulette.setUpFields();
    static String returnString;

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
                checkWin(ColorEnum.RED);
                openRotateActivity();
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWin(ColorEnum.BLACK);
                openRotateActivity();
            }
        });
    }

    public int checkWin(ColorEnum choosenColor){
        //calculates if user wins or not

        int money = 1000000; //nur zu Testzwecken

        double rouletteNumber = roulette.spinIt();

        for(int i = 0; i < array.length; i++){
            if(rouletteNumber == array[i].getValue()){
                if(array[i].getColor() == choosenColor){
                    money = money + 30000;  //--> 80000-50000 Einsatz
                    //ODER muss Kontostand ständig angezeigt werden?
                    this.returnString = "Du hast gewonnen!";
                }
                else{
                    money = money - 5000; //Einsatz
                    this.returnString = "Du hast verloren.";
                }
            }
        } return money;
    }

    public static String getReturnString(){
        return returnString;
    }

    public static void setReturnString(String newReturnString){
        returnString = newReturnString;
    }

    public void openRotateActivity(){
        Intent it = new Intent(this, RotateActivity.class);
        startActivity(it);
    }
}
