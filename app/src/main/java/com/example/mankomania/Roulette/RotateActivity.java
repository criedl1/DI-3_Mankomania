package com.example.mankomania.Roulette;

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
    private static int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

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
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                FieldClass field = RouletteClass.getTheField();
                float degree = field.getDegree();

                final RotateAnimation finalRotate = new RotateAnimation(0.0f, 360 + degree*(-1),
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                finalRotate.setDuration(1000);
                finalRotate.setFillAfter(true);
                imageView.startAnimation(finalRotate);
                imageView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openPopUp();
                    }
                },2000);
            }
        });
        }

    private void openPopUp() {
       PopClass popClass = new PopClass();
       popClass.show(getSupportFragmentManager(), "alert");

    }

    public static int getMoney(){
        return money;
    }


}
