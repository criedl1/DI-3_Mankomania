package com.example.mankomania.roulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mankomania.R;

public class RotateActivity extends AppCompatActivity {

    private Animation rotateAnimation;
    private ImageView imageView;
    private Button btnBack;
    private int money;
    private float degree;

    //TODO: Finding a way to give these variables to PopClass without static
    private static String returnString;
    private static String color;
    private static int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        Intent it = getIntent();
        Bundle extras = it.getExtras();
        money = extras.getInt("money");
        returnString = extras.getString("returnString");
        color = extras.getString("color");
        randomNumber = extras.getInt("randomNumber");
        degree = extras.getFloat("degree");

        btnBack = findViewById(R.id.btnBack);
        btnBack.setText(getString(R.string.roulette_back));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int touch = MotionEventCompat.getActionMasked(event);

                if(touch == MotionEvent.ACTION_MOVE){
                        rotateAnimation();
                    }
                    return true; //since this is an override method, i am not able to make return type void.
                }
        });
    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                //no need for this, but have to override it because AnimationListener is an interface

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //no need for this, but have to override it because AnimationListener is an interface
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                final RotateAnimation finalRotate = new RotateAnimation(0.0f, 360 + degree * (-1),
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                finalRotate.setDuration(1000);
                finalRotate.setFillAfter(true);
                imageView.startAnimation(finalRotate);
                imageView.postDelayed(createRunnable(), 2000);
            }
        });
    }

        private Runnable createRunnable() {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    openPopUp();
                }
            };
            return runnable;
        }

    private void openPopUp() {
       PopClass popClass = new PopClass();
       popClass.show(getSupportFragmentManager(), "alert");

    }

    //TODO: Ändern, wenn Server das Zeug nicht übernimmt
    public int getMoney(){
        return money;
    }

    protected static String getReturnString(){
        return returnString;
    }

    protected static String getColor(){
        return color;
    }

    public static int getRandomNumber() {
        return randomNumber;
    }
}
