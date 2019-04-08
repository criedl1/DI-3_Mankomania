package com.example.mankomania;

import android.content.Intent;
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
                openPopUpActivity();
            }
        }, 2500);
    }

    public void openPopUpActivity() {
        Intent it = new Intent(this, PopClass.class);
        startActivity(it);
    }
}
