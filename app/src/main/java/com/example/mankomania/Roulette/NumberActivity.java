package com.example.mankomania.Roulette;

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

    RouletteClass roulette = new RouletteClass();
    EditText number;
    TextView selectNumber;
    Button go;
    int choosenNumber;
    static String returnString;
    static int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        number = findViewById(R.id.etInsertNumber);
        selectNumber = findViewById(R.id.tvSelectNumber);
        go = findViewById(R.id.btnGo);

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

    public int spinWheel(int choosenNumber) {

        int rouletteNumber = roulette.spinIt();

        if (rouletteNumber == choosenNumber) {
            money = 145000;  //--> 150000 - 5000 Einsatz

            returnString = getString(R.string.roulette_won, money);
        } else {
            money = - 50000; //Einsatz
            returnString = getString(R.string.roulette_lost, money*-1);
        }
        this.sendMoneyChange(money);
        return money;
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

    public void openErrorPopUp() {
       ErrorClass error = new ErrorClass();
       error.show(getSupportFragmentManager(), "alert");
    }

    public static int getMoney(){
        return money;
    }
}

