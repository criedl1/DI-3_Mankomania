package com.example.mankomania.Dice;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mankomania.Map.MapView;
import com.example.mankomania.R;

import java.util.Random;

import static android.content.Context.SENSOR_SERVICE;

public class Dice extends Fragment implements SensorEventListener {

    private Button btnClose;
    private boolean bool1;
    private ImageView ivDice1, ivDice2;
    MediaPlayer mediaPlayer;
    int result;
    int[][][] diceResults = {
            {{1,1}},
            {{1,2},{2,1}},
            {{1,3},{3,1},{2,2}},
            {{1,4},{4,1},{2,3},{3,2}},
            {{1,5},{5,1},{2,4},{4,2}, {3,3}},
            {{1,6},{6,1}, {5,2}, {2,5}, {3,4}, {4,3}},
            {{2,6}, {6,2}, {3,5}, {5,3}, {4,4}},
            {{3,6}, {6,3}, {4,5}, {5,4}},
            {{4,6}, {6,4}, {5,5}},
            {{5,6}, {6,5}},
            {{6,6}}};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hides the titlebar
        //getSupportActionBar().hide();
        //sets the app to Fullscreen
        this.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Create Sensormanager
        SensorManager mySensormanager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        // Accelerometersensor
        Sensor mySensor = mySensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //register Sensorlistener
        mySensormanager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        // assign Button
        // initialize Random and bool
        bool1 = false;

        return inflater.inflate(R.layout.activity_dice, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ivDice1 = getActivity().findViewById(R.id.ivDice1);
        ivDice2 = getActivity().findViewById(R.id.ivDice2);
        btnClose = getActivity().findViewById(R.id.btnClose);
        // assign ImageViews
        ivDice1.animate().scaleX(0).scaleY(0);
        ivDice2.animate().scaleX(0).scaleY(0);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        // sum of sensors
        float acceleration = (x + y + z);
        // if sum > 70 --> roll the Dice
        if (acceleration > 60 && !bool1) {
            //rollTheDice();
            bool1 = true;
            ((MapView)getActivity()).sendRollDice();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not in use
    }

    public void showResult(int result) {
        this.result = result;
        //servercall draus machen + neuer call show result von server
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dice);
        mediaPlayer.start();
        int[][] diceResult = diceResults[result-2];
        int[] ddiceResult = diceResult[new Random().nextInt(diceResult.length-1)];
        Toast.makeText(getActivity(), "Du hast " + result+ " gewürfelt", Toast.LENGTH_SHORT).show();
        try {
            switch (ddiceResult[0]) {
                case 1:
                    ivDice1.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    ivDice1.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    ivDice1.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    ivDice1.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    ivDice1.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    ivDice1.setImageResource(R.drawable.dice6);
                    break;
                default:
                    throw new RuntimeException("unreachable");
            }
        } catch (RuntimeException e) {
            System.out.println("unreachable");
        }
        try {
            switch (ddiceResult[1]) {
                case 1:
                    ivDice2.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    ivDice2.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    ivDice2.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    ivDice2.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    ivDice2.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    ivDice2.setImageResource(R.drawable.dice6);
                    break;
                default:
                    throw new RuntimeException("unreachable");
            }
        } catch (RuntimeException e) {
            System.out.println("unreachable");
        }
        ivDice1.animate().scaleX(0.8f).scaleY(0.8f).setDuration(1300);
        ivDice2.animate().scaleX(0.8f).scaleY(0.8f).setDuration(1300);
        btnClose.setVisibility(View.VISIBLE);
    }

}
