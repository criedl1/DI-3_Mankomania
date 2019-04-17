package com.example.mankomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class RotateActivity extends AppCompatActivity {

    Animation rotateAnimation;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        imageView = (ImageView) findViewById(R.id.imageView);

        rotateAnimation();

    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotateAnimation.hasEnded();
        imageView.startAnimation(rotateAnimation);
        imageView.postDelayed(new Runnable() {

            @Override
            public void run() {
                openPopUp();
            }
        }, 2500);

    }

    public void openPopUp() {
       PopClass popClass = new PopClass();
       popClass.show(getSupportFragmentManager(), "alert");
    }
}
