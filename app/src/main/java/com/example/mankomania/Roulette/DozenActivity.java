package com.example.mankomania.Roulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mankomania.R;

public class DozenActivity extends AppCompatActivity {

    RouletteClass roulette = new RouletteClass();
    FieldClass[] array = roulette.setUpFields();

    TextView selectDozen;
    Button btn1;
    Button btn13;
    Button btn25;

    static String returnString;
    static int money;

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
                openRotateActivity();
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel(2);
                openRotateActivity();
            }
        });

        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel(3);
                openRotateActivity();
            }
        });
    }

    public void spinWheel(int choosenDozen){

        int rouletteNumber = roulette.spinIt();
        int dozen = 0;

        for(int i = 0; i < array.length; i++){
            if(array[i].getValue() == rouletteNumber){
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
            money = 80000; //100000 - 20000 Einsatz

            returnString = "Du hast " + money + " gewonnen!";}

        else {
            money = - 20000;
            returnString = "Du hast " + money*(-1) + " verloren!";
        }
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
        finish();
    }

    public static int getMoney(){
        return money;
    }
}
