package com.example.mankomania.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mankomania.network.client.Client;
import com.example.mankomania.R;
import com.google.gson.JsonObject;

public class NumberActivity extends AppCompatActivity {

    private RouletteClass roulette = new RouletteClass();
    private EditText number;
    private int choosenNumber;
    private String returnString;
    private int money;

    //For Network:
    public static int moneyAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        number = findViewById(R.id.etInsertNumber);
        TextView selectNumber = findViewById(R.id.tvSelectNumber);
        Button go = findViewById(R.id.btnGo);

        selectNumber.setText(getString(R.string.roulette_choose_number));
        number.setHint(getString(R.string.roulette_enter_number));
        go.setText(getString(R.string.roulette_set));

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenNumber = Integer.valueOf(number.getText().toString());
                if (choosenNumber > 36 || choosenNumber < 0 || number.getText().toString().isEmpty()){
                    openErrorPopUp();
                }
                else {
                    spinWheel(choosenNumber);
                    openRotateActivity();
                }
            }
        });
    }

    private int spinWheel(int choosenNumber) {

        int rouletteNumber = roulette.spinIt();

        if (rouletteNumber == choosenNumber) {
            setMoney(145000);  //--> 150000 - 5000 Einsatz
            moneyAmount = money;

            returnString = getString(R.string.roulette_won, getMoney());
        } else {
            setMoney(- 50000); //Einsatz
            moneyAmount = getMoney();
            returnString = getString(R.string.roulette_lost, getMoney()*-1);
        }
        this.sendMoneyChange(money);
        return money;
    }

    private int getRandomNumberFromRouletteClass(){
        return roulette.getRandomNumber();
    }

    private ColorEnum getColorFromRouletteClass(){
        return roulette.getTheField().getColor();
    }

    private float getDegreeFromRouletteClass(){
        return roulette.getTheField().getDegree();
    }

    private void openRotateActivity(){
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

    private void sendMoneyChange(int rouletteResult){
        JsonObject object = new JsonObject();
        object.addProperty("result", rouletteResult);
        object.addProperty("OPERATION", "ROULETTERESULT");
        Intent intent = new Intent("client.update");
        intent.putExtra("result", object.toString());
        LocalBroadcastManager.getInstance(Client.mapView)
                .sendBroadcast(intent);
    }

    private void openErrorPopUp() {
        ErrorClass error = new ErrorClass();
        error.show(getSupportFragmentManager(), "alert");
    }

    private void setMoney(int money){
        this.money = money;
    }

    private int getMoney(){
        return money;
    }

    public String getReturnString() {
        return returnString;
    }

    public static int getMoneyAmount(){
        //did this, because i want to work with non-static variables in my classes

        return moneyAmount;
    }


}