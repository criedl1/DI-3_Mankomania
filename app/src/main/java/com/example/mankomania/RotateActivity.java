package com.example.mankomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
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
        imageView.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

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

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        }

    public void openPopUp() {
       PopClass popClass = new PopClass();
       popClass.show(getSupportFragmentManager(), "alert");
    }
}
