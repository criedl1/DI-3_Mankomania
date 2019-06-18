package com.example.mankomania.dice;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mankomania.R;
import com.example.mankomania.map.MapView;

import java.util.Random;

import static android.content.Context.SENSOR_SERVICE;

public class Dice extends Fragment implements SensorEventListener {
    private boolean diceRolled;
    MediaPlayer mediaPlayer;
    int result;
    Random random;

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
        random = new Random();

        Toast.makeText(getActivity(), "Bitte schütteln", Toast.LENGTH_SHORT).show();
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
        if (acceleration > 10 && !diceRolled) {
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
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dice);
        mediaPlayer.start();
        ImageView ivDice1 = getActivity().findViewById(R.id.ivDice1);
        ImageView ivDice2 = getActivity().findViewById(R.id.ivDice2);
        Button btnClose = getActivity().findViewById(R.id.btnClose);


        @DrawableRes int[] wuerfelImages = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6,

        };

        int[] ddiceResult = ResultSplitter.splitResult(result);
        Toast.makeText(getActivity(), "Du hast " + result + " gewürfelt", Toast.LENGTH_SHORT).show();

        ivDice1.setImageResource(wuerfelImages[ddiceResult[0]-1]);
        ivDice2.setImageResource(wuerfelImages[ddiceResult[1]-1]);

        ivDice1.animate().scaleX(0.8f).scaleY(0.8f).setDuration(1300);
        ivDice2.animate().scaleX(0.8f).scaleY(0.8f).setDuration(1300);
        btnClose.setVisibility(View.VISIBLE);
    }

}
