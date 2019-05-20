package com.example.mankomania.roulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mankomania.R;

public class MainActivityRoulette extends AppCompatActivity {

    String choosenActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainroulette);

        TextView welcome = findViewById(R.id.tvWillkommen);
        Button color = findViewById(R.id.btnFarbe);
        Button number = findViewById(R.id.btnZahl);
        Button dozen = findViewById(R.id.btnDutzend);
        TextView tv1 = findViewById(R.id.textView1);
        TextView tv2 = findViewById(R.id.textView2);
        TextView tv3 = findViewById(R.id.textView3);


        welcome.setText(getString(R.string.roulette_welcome));
        color.setText(getString(R.string.roulette_set_color));
        number.setText(getString(R.string.roulette_set_number));
        dozen.setText(getString(R.string.roulette_set_dozen));

        tv1.setText(getString(R.string.roulette_set_money,5000));
        tv2.setText(getString(R.string.roulette_set_money,50000));
        tv3.setText(getString(R.string.roulette_set_money,20000));

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenActivity("Color");
                askQuestion(getChoosenActivity());
            }
        });

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenActivity("Number");
                askQuestion(getChoosenActivity());
            }
        });

        dozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosenActivity("Dozen");
                askQuestion(getChoosenActivity());
            }
        });
    }

    public String getChoosenActivity(){
        return this.choosenActivity;
    }

    public void setChoosenActivity(String choosenActivity){
        this.choosenActivity = choosenActivity;
    }

    //TODO: Work this out
    public void askQuestion(String choosenActivity){
        Intent it = new Intent(this, QuestionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("choosenActivity", choosenActivity);
        it.putExtras(bundle);
        startActivity(it);
        finish();
    }
}