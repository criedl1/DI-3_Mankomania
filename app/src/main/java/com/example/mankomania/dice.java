package com.example.mankomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class dice extends AppCompatActivity implements SensorEventListener{

    //private TextView xText, yText, zText,txtDice,txtDice1,txtDice2;
    private Sensor mySensor;
    private SensorManager mySensormanager;
    private Button btnMax;
    private Random rand;
    private boolean bool1;
    private ImageView ivDice1,ivDice2;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //hides the titlebar
        getSupportActionBar().hide();
        //sets the app to Fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create Sensormanager
        mySensormanager = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometersensor
        mySensor = mySensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //register Sensorlistener
        mySensormanager.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);


        // assign ImageViews
        ivDice1 = findViewById(R.id.ivDice1);
        ivDice2 = findViewById(R.id.ivDice2);
        ivDice1.animate().scaleX(0).scaleY(0);
        ivDice2.animate().scaleX(0).scaleY(0);
        // assign Button
        btnMax = findViewById(R.id.btnMax);

        // initialize Random and bool
        rand = new Random();
        bool1=false;
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];


        // sum of sensors
        float acceleration = (x + y + z);

        // if sum > 70 --> roll the dice
        if (acceleration>70 && !bool1){
            rollTheDice();
        }
    }
    public void rollTheDice() throws RuntimeException{
        //servercall draus machen + neuer call show result von server
        mediaPlayer = MediaPlayer.create(this,R.raw.dice);
        mediaPlayer.start();
        int dice1 = rand.nextInt(6)+1;
        int dice2 = rand.nextInt(6)+1;
        bool1=true;
        Toast.makeText(this,"Du hast " + (dice1+dice2) +" gewürfelt",Toast.LENGTH_SHORT).show();

        try{
        switch (dice1) {
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
        }catch (RuntimeException e){
            System.out.println("unreachable");
        }
        try {
            switch (dice2) {
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
        }catch (RuntimeException e){
            System.out.println("unreachable");
        }
        ivDice1.animate().scaleX(0.8f).scaleY(0.8f).setDuration(1300);
        ivDice2.animate().scaleX(0.8f).scaleY(0.8f).setDuration(1300);
    }
    public void setBackDice(View view){


        bool1=false;
        ivDice1.animate().scaleX(0).scaleY(0).setDuration(0);
        ivDice2.animate().scaleX(0).scaleY(0).setDuration(0);

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not in use
    }


}
