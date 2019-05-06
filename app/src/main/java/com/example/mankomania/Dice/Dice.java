package com.example.mankomania.Dice;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private boolean diceRolled;
    MediaPlayer mediaPlayer;
    int result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        diceRolled = false;

        return inflater.inflate(R.layout.activity_dice, container, false);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        // sum of sensors
        float acceleration = (x + y + z);
        // if sum > 70 --> roll the Dice
        if (acceleration > 20 && !diceRolled) {
            //rollTheDice();
            diceRolled = true;
            ((MapView) getActivity()).sendRollDice();
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
        ImageView ivDice1 = getActivity().findViewById(R.id.ivDice1);
        ImageView ivDice2 = getActivity().findViewById(R.id.ivDice2);
        Button btnClose = getActivity().findViewById(R.id.btnClose);

        int[] ddiceResult = new int[2];
        Random r = new Random();
        if (result >= 7) {
            ddiceResult[0] = (result - 6 + r.nextInt(Math.abs(result - 12) + 1));
            ddiceResult[1] = result - ddiceResult[0];
        } else {
            ddiceResult[0] = r.nextInt(result - 1) + 1;
            ddiceResult[1] = result - ddiceResult[0];
        }
        Toast.makeText(getActivity(), "Du hast " + result + " gewürfelt", Toast.LENGTH_SHORT).show();
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
