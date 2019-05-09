package com.example.mankomania.roulette;

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

    private RouletteClass roulette = new RouletteClass();
    private FieldClass[] array = roulette.setUpFields();
    private String returnString;
    private int money;

    //for Network
    private static int moneyAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        Button red = findViewById(R.id.btnRed);
        Button black = findViewById(R.id.btnBlack);
        TextView selectColor = findViewById(R.id.tvSelectColor);

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

    private void checkWin(ColorEnum choosenColor) {
        //calculates if user wins or not

        double rouletteNumber = roulette.spinIt();

        for (FieldClass anArray : array) {
            if (rouletteNumber == anArray.getValue()) {
                if (anArray.getColor() == choosenColor) {
                    setMoney(30000); //--> 80000-50000 Einsatz
                    setReturnString(getString(R.string.roulette_won, getMoney()));
                    moneyAmount = getMoney();

                } else {
                    setMoney(-5000); //Einsatz
                    setReturnString(getString(R.string.roulette_lost, getMoney() * -1));
                    moneyAmount = getMoney();
                }
                sendMoneyChange(getMoney());
            }
        }
    }

    private int getRandomNumberFromRouletteClass(){
        return roulette.getRandomNumber();
    }

    private float getDegreeFromRouletteClass(){
        return roulette.getTheField().getDegree();
    }

    private ColorEnum getColorFromRouletteClass(){
        return roulette.getTheField().getColor();
    }

    private void openRotateActivity() {
        Bundle extras = new Bundle();
        Intent it = new Intent(this, RotateActivity.class);
        extras.putString("returnString", getReturnString());
        extras.putInt("money", getMoney());
        extras.putInt("randomNumber", getRandomNumberFromRouletteClass());
        extras.putSerializable("color", getColorFromRouletteClass());
        extras.putFloat("degree", getDegreeFromRouletteClass());
        it.putExtras(extras);
        startActivity(it);
        finish();
    }

    private void sendMoneyChange(int rouletteResult) {
        JsonObject object = new JsonObject();
        object.addProperty("result", rouletteResult);
        object.addProperty("OPERATION", "ROULETTERESULT");
        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.mapView)
                .sendBroadcast(intent);
    }

    protected int getMoney() {
        return money;
    }

    protected void setMoney(int newMoney) {
        money = newMoney;
    }

    public String getReturnString() {
        return returnString;
    }

    protected void setReturnString(String returnString){
        this.returnString = returnString;
    }

    public static int getMoneyAmount(){
        //did this, because i want to work with non-static variables in my classes
        return moneyAmount;
    }
}
