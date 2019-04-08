package com.example.mankomania;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PopClass extends Activity {

    RouletteClass roulette = new RouletteClass();
    PopupWindow popup= new PopupWindow(this);
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*0.8), (int) (height*0.6));
        double number = roulette.getRandomNumber();
        String numberString = Double.toString(number);

       // ((TextView)popup.getContentView().findViewById(R.id.tvPopUp)).setText("Die Zahl lautet" + numberString);
    }
}
