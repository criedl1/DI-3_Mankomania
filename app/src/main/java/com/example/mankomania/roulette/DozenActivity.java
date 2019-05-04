package com.example.mankomania.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mankomania.Network.Client.Client;
import com.example.mankomania.R;
import com.google.gson.JsonObject;

public class DozenActivity extends AppCompatActivity {

    private RouletteClass roulette = new RouletteClass();
    private FieldClass[] array = roulette.setUpFields();

    private TextView selectDozen;
    private Button btn1;
    private Button btn13;
    private Button btn25;

    private String returnString;
    private int money;

    //for network
    public static int moneyAmount;

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

    private void spinWheel(int choosenDozen){

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
            setMoney(80000); //100000 - 20000 Einsatz
            setReturnString(getString(R.string.roulette_won, getMoney()));
            moneyAmount = getMoney();}
        else {
            setMoney(- 20000);
            setReturnString(getString(R.string.roulette_lost, getMoney() * -1));
            moneyAmount = getMoney();
        }
        this.sendMoneyChange(getMoney());
    }

    private void openRotateActivity(){
        Bundle extras = new Bundle();
        Intent it = new Intent(this, RotateActivity.class);
        extras.putString("returnString", returnString);
        extras.putInt("money", getMoney());
        extras.putInt("randomNumber", getRandomNumberFromRouletteClass());
        extras.putSerializable("color", getColorFromRouletteClass());
        extras.putFloat("degree", getDegreeFromRouletteClass());
        it.putExtras(extras);
        startActivity(it);
        finish();
    }

    private int getRandomNumberFromRouletteClass(){
        return roulette.getRandomNumber();
    }

    private float getDegreeFromRouletteClass(){
        return roulette.getTheField().getDegree();
    }

    private ColorEnum getColorFromRouletteClass(){
        return roulette.getTheField().getColor();}

    private void sendMoneyChange(int rouletteResult){
        JsonObject object = new JsonObject();
        object.addProperty("result", rouletteResult);
        object.addProperty("OPERATION", "ROULETTERESULT");
        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.MapView)
                .sendBroadcast(intent);
    }

    protected int getMoney(){
        return money;
    }

    protected void setReturnString(String returnString){
        this.returnString = returnString;
    }

    private void setMoney(int money){
        this.money = money;
    }

    public static int getMoneyAmount(){
        //did this, because i want to work with non-static variables in my classes
        return moneyAmount;
    }
}
