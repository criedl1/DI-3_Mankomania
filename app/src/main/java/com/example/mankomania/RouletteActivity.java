package com.example.mankomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class RouletteActivity extends AppCompatActivity {

    Animation rotate;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);

        iv = (ImageView) findViewById(R.id.imageView);
    }

    public Field[] setUpFields() {
        Field field0 = new Field(enum_color.GREEN, 0);
        Field field32 = new Field(enum_color.RED, 32);
        Field field15 = new Field(enum_color.BLACK, 15);
        Field field19 = new Field(enum_color.RED, 19);
        Field field4 = new Field(enum_color.BLACK, 4);
        Field field21 = new Field(enum_color.RED, 21);
        Field field2 = new Field(enum_color.BLACK, 2);
        Field field25 = new Field(enum_color.RED, 25);
        Field field17 = new Field(enum_color.BLACK, 17);
        Field field34 = new Field(enum_color.RED, 34);
        Field field6 = new Field(enum_color.BLACK, 6);
        Field field27 = new Field(enum_color.RED, 27);
        Field field13 = new Field(enum_color.BLACK, 13);
        Field field36 = new Field(enum_color.RED, 36);
        Field field11 = new Field(enum_color.BLACK, 11);
        Field field30 = new Field(enum_color.RED, 30);
        Field field8 = new Field(enum_color.BLACK, 8);
        Field field23 = new Field(enum_color.RED, 23);
        Field field10 = new Field(enum_color.BLACK, 10);
        Field field5 = new Field(enum_color.RED, 5);
        Field field24 = new Field(enum_color.BLACK, 24);
        Field field16 = new Field(enum_color.RED, 16);
        Field field33 = new Field(enum_color.BLACK, 33);
        Field field1 = new Field(enum_color.RED, 1);
        Field field20 = new Field(enum_color.BLACK, 20);
        Field field14 = new Field(enum_color.RED, 14);
        Field field31 = new Field(enum_color.BLACK, 31);
        Field field9 = new Field(enum_color.RED, 9);
        Field field22 = new Field(enum_color.BLACK, 22);
        Field field18 = new Field(enum_color.RED, 18);
        Field field29 = new Field(enum_color.BLACK, 29);
        Field field7 = new Field(enum_color.RED, 7);
        Field field28 = new Field(enum_color.BLACK, 28);
        Field field12 = new Field(enum_color.RED, 12);
        Field field35 = new Field(enum_color.BLACK, 35);
        Field field3 = new Field(enum_color.RED, 3);
        Field field26 = new Field(enum_color.BLACK, 26);


        Field[] array = {field0, field32, field15, field19, field4, field21, field2, field25, field17,
                field34, field6, field27, field13, field36, field11, field30, field8, field23,
                field10, field5, field24, field16, field33, field1, field20, field14, field31,
                field9, field22, field18, field29, field7, field28, field12, field35, field3,
                field26};

        return array;
    }

    public static double randomNumber(){
        double randomNumber = (int) (Math.random() *36) + 0;
        return randomNumber;
    }

    public void startRotating () {

        try {
            rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
            iv.startAnimation(rotate);
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}
