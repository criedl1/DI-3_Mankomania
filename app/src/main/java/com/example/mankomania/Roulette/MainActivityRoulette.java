package com.example.mankomania.Roulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mankomania.R;

public class MainActivityRoulette extends AppCompatActivity {


    private TextView welcome;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button color;
    private Button number;
    private Button dozen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainroulette);

        welcome = findViewById(R.id.tvWillkommen);
        color = findViewById(R.id.btnFarbe);
        number = findViewById(R.id.btnZahl);
        dozen = findViewById(R.id.btnDutzend);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);


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
                openColorActivity();
            }
        });

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNumberActivity();
            }
        });

        dozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDozenActivity();
            }
        });
    }

    private void openColorActivity(){
        Intent it = new Intent(this, ColorActivity.class);
        startActivity(it);
        finish();
    }

    private void openNumberActivity(){
        Intent it = new Intent(this, NumberActivity.class);
        startActivity(it);
        finish();
    }

    private void openDozenActivity(){
        Intent it = new Intent(this, DozenActivity.class);
        startActivity(it);
        finish();
    }
}
