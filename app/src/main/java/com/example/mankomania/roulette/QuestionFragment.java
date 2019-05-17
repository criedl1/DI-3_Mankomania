package com.example.mankomania.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mankomania.R;

public class QuestionFragment extends Fragment {

    private int choosenNumber;
    private ColorEnum choosenColor;
    private String choosenDozen;
    private RouletteLogic roulette;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.activity_question, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        final String choosenActivity = bundle.getString("choosenActivity");

        if (choosenActivity.equals("Number")) {
            getNumberSetUp();
        }

        else if(choosenActivity.equals("Color")){
            getColorSetUp();
        }

        else if (choosenActivity.equals("Dozen")){
            getDozenSetUp();
        }

        else {
            throw new IllegalArgumentException();
        }
    }

    private void openErrorPopUp() {
        ErrorPopUp error = new ErrorPopUp();
        error.show(getFragmentManager(), "alert");
    }

    public void getNumberSetUp(){
        final EditText number = getActivity().findViewById(R.id.etInsertNumber);
        final TextView selectNumber = getActivity().findViewById(R.id.tvSelectNumber);
        Button go = getActivity().findViewById(R.id.btnGo);

        selectNumber.setText(getString(R.string.roulette_choose_number));
        number.setHint(getString(R.string.roulette_enter_number));
        go.setText(getString(R.string.roulette_set));

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenNumber = Integer.valueOf(number.getText().toString());
                if (choosenNumber > 36 || choosenNumber < 0 || number.getText().toString().isEmpty()) {
                    openErrorPopUp();
                } else {
                   roulette = new RouletteLogic(choosenNumber);
                }
            }
        });
    }

    public void getColorSetUp(){
        Button red = getActivity().findViewById(R.id.btnRed);
        Button black = getActivity().findViewById(R.id.btnBlack);
        TextView selectColor = getActivity().findViewById(R.id.tvSelectColor);

        red.setText(getString(R.string.color_red));
        black.setText(getString(R.string.color_black));
        selectColor.setText(getString(R.string.choose_roulette_color));

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenColor = ColorEnum.RED;
                roulette = new RouletteLogic(choosenColor);
                finish();
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenColor = ColorEnum.BLACK;
                finish();
            }
        });
    }

    public void getDozenSetUp(){
        TextView selectDozen = getActivity().findViewById(R.id.tvSelectDozen);

        Button btn1 = getActivity().findViewById(R.id.btn1_12);
        Button btn13 = getActivity().findViewById(R.id.btn13_24);
        Button btn25 = getActivity().findViewById(R.id.btn25_36);

        selectDozen.setText(getString(R.string.roulette_choose_dozen));

        btn1.setText(getString(R.string.roulette_first_dozen));
        btn13.setText(getString(R.string.roulette_second_dozen));
        btn25.setText(getString(R.string.roulette_third_dozen));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenDozen = "1";
                //TODO: Check, if Integer.parseInt works correctly!!
                roulette = new RouletteLogic(choosenDozen);
                finish();
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenDozen = "2";
                roulette = new RouletteLogic(choosenDozen);
                finish();
            }
        });

        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenDozen = "3";
                roulette = new RouletteLogic(choosenDozen);
                finish();
            }
        });
    }

    private void finish() {
        Intent it = new Intent(getActivity(), RotateActivity.class);
        startActivity(it);
    }
}