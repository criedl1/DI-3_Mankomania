package com.example.mankomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView welcome;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    Button color;
    Button number;
    Button dozen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.tvWillkommen);
        color = findViewById(R.id.btnFarbe);
        number = findViewById(R.id.btnZahl);
        dozen = findViewById(R.id.btnDutzend);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);


        welcome.setText("Willkommen beim Roulette! Worauf möchtest du setzen?");
        color.setText("auf Farbe setzen");
        number.setText("auf Zahl setzen");
        dozen.setText("auf ein Dutzend setzen");

        tv1.setText("Einsatz: 5.000€");
        tv2.setText("Einsatz: 50.000€");
        tv3.setText("Einsatz: 20.000€");


        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openColorActivity();
            }
        });

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openNumberActivity();
            }
        });

        dozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDozenActivity();
            }
        });
    }
}
