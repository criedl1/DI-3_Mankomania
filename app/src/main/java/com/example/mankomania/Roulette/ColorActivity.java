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

public class ColorActivity extends AppCompatActivity {

    RouletteClass roulette = new RouletteClass();
    FieldClass[] array = roulette.setUpFields();
    static String returnString;
    static int money;

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

        red.setText(getString(R.string.color_red));
        black.setText(getString(R.string.color_black));
        selectColor.setText(getString(R.string.choose_roulette_color));

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

    public void checkWin(ColorEnum choosenColor){
        //calculates if user wins or not

        double rouletteNumber = roulette.spinIt();

        for (FieldClass anArray : array) {
            if (rouletteNumber == anArray.getValue()) {
                if (anArray.getColor() == choosenColor) {
                    money = 30000;  //--> 80000-50000 Einsatz
                    returnString = getString(R.string.roulette_won, money);
                } else {
                    money = -5000; //Einsatz
                    returnString = getString(R.string.roulette_lost, money * -1);
                }
                sendMoneyChange(money);
            }
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
