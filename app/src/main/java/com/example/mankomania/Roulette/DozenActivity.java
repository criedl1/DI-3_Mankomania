package com.example.mankomania.Roulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mankomania.network.client.Client;
import com.example.mankomania.R;
import com.google.gson.JsonObject;

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

        selectDozen.setText(getString(R.string.roulette_choose_dozen));
        btn1.setText(getString(R.string.roulette_first_dozen));
        btn13.setText(getString(R.string.roulette_second_dozen));
        btn25.setText(getString(R.string.roulette_third_dozen));

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

        for (FieldClass fieldClass : array) {
            if (fieldClass.getValue() == rouletteNumber) {
                if (fieldClass.getValue() <= 12) {
                    dozen = 1;
                } else if (fieldClass.getValue() <= 24 && fieldClass.getValue() > 12) {
                    dozen = 2;
                } else {
                    dozen = 3;
                }
            }
        }

        if (choosenDozen == dozen){
            money = 80000; //100000 - 20000 Einsatz

            returnString = "Du hast " + money + " gewonnen!";}

        else {
            money = - 20000;
            returnString = "Du hast " + money*(-1) + " verloren!";
        }
        this.sendMoneyChange(money);
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

    private void sendMoneyChange(int rouletteResult){
        JsonObject object = new JsonObject();
        object.addProperty("result", rouletteResult);
        object.addProperty("OPERATION", "ROULETTERESULT");
        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.mapView)
                .sendBroadcast(intent);
    }

    public static int getMoney(){
        return money;
    }
}
