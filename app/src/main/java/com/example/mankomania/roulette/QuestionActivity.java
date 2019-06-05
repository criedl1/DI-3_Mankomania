package com.example.mankomania.roulette;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mankomania.R;

public class QuestionActivity extends AppCompatActivity {
    private int choosenNumber;
    private ColorEnum choosenColor;
    private String choosenDozen;
    private RouletteLogic roulette;
    private int slotMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent it = getIntent();
        Bundle extras = it.getExtras();
        String choosenActivity = extras.getString("choosenActivity");
        slotMoney = extras.getInt("slotMoney");

        if (choosenActivity.equals("Number")) {
            getNumberSetUp();
        } else if (choosenActivity.equals("Color")) {
            getColorSetUp();
        } else if (choosenActivity.equals("Dozen")) {
            getDozenSetUp();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void openErrorPopUp() {
        PopUp error = new PopUp();
        Bundle extras = new Bundle();
        extras.putString("popUpFor", "Error");
        error.setArguments(extras);

        error.show(getSupportFragmentManager(), "alert");

    }

    public void getNumberSetUp() {
        final EditText number = findViewById(R.id.etInsertNumber);
        number.setVisibility(View.VISIBLE);
        final TextView selectNumber = findViewById(R.id.tvSelectNumber);
        selectNumber.setVisibility(View.VISIBLE);
        Button go = findViewById(R.id.btnGo);
        go.setVisibility(View.VISIBLE);

        selectNumber.setText(getString(R.string.roulette_choose_number));
        number.setHint(getString(R.string.roulette_enter_number));
        go.setText(getString(R.string.roulette_set));

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    choosenNumber = Integer.valueOf(number.getText().toString());

                    if (choosenNumber <= 36 && choosenNumber >= 0) {
                        roulette = new RouletteLogic(choosenNumber, slotMoney);
                        startRotating();
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    openErrorPopUp();
                }
            }
        });
    }

    public void getColorSetUp() {
        Button red = findViewById(R.id.btnRed);
        red.setVisibility(View.VISIBLE);
        Button black = findViewById(R.id.btnBlack);
        black.setVisibility(View.VISIBLE);
        TextView selectColor = findViewById(R.id.tvSelectColor);
        selectColor.setVisibility(View.VISIBLE);

        red.setText(getString(R.string.color_red));
        black.setText(getString(R.string.color_black));
        selectColor.setText(getString(R.string.choose_roulette_color));

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenColor = ColorEnum.RED;
                roulette = new RouletteLogic(choosenColor, slotMoney);
                startRotating();
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenColor = ColorEnum.BLACK;
                roulette = new RouletteLogic(choosenColor, slotMoney);
                startRotating();
            }
        });
    }

    public void getDozenSetUp() {
        TextView selectDozen = findViewById(R.id.tvSelectDozen);

        Button btn1 = findViewById(R.id.btn1_12);
        btn1.setVisibility(View.VISIBLE);
        Button btn13 = findViewById(R.id.btn13_24);
        btn13.setVisibility(View.VISIBLE);
        Button btn25 = findViewById(R.id.btn25_36);
        btn25.setVisibility(View.VISIBLE);

        selectDozen.setText(getString(R.string.roulette_choose_dozen));
        selectDozen.setVisibility(View.VISIBLE);

        btn1.setText(getString(R.string.roulette_first_dozen));
        btn13.setText(getString(R.string.roulette_second_dozen));
        btn25.setText(getString(R.string.roulette_third_dozen));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenDozen = "1";
                roulette = new RouletteLogic(choosenDozen, slotMoney);
                startRotating();
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenDozen = "2";
                roulette = new RouletteLogic(choosenDozen, slotMoney);
                startRotating();
            }
        });

        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenDozen = "3";
                roulette = new RouletteLogic(choosenDozen, slotMoney);
                startRotating();
            }
        });
    }

    private void startRotating() {
        Intent it = new Intent(this, RotateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("returnString", roulette.getReturnString());
        bundle.putSerializable("Color", roulette.getColorFromRoulette());
        bundle.putInt("randomNumber", roulette.getRandomNumberFromRoulette());
        bundle.putFloat("degree", roulette.getDegreeFromRoulette());
        bundle.putString("colorString", roulette.getColorString());
        bundle.putInt("money", roulette.getMoney());
        it.putExtras(bundle);
        startActivity(it);
        finish();
    }
}
