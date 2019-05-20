package com.example.mankomania.slotmachine;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mankomania.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SlotMachineActivity extends AppCompatActivity {

    public int money;
    private List<Symbol> slotList = new ArrayList<>();
    private Symbol cherry = new Symbol(R.drawable.cherry, 0);
    private Symbol dollar = new Symbol(R.drawable.dollar, 1);
    private Symbol star = new Symbol(R.drawable.star, 2);
    private Symbol seven = new Symbol(R.drawable.sieben, 3);
    private ImageView slot1;
    private ImageView slot2;
    private ImageView slot3;
    private Button spin;
    private String returnString;
    private String winString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        slot1 = findViewById(R.id.ivSlot1);
        slot2 = findViewById(R.id.ivSlot2);
        slot3 = findViewById(R.id.ivSlot3);

        slotList.add(cherry);
        slotList.add(star);
        slotList.add(seven);
        slotList.add(dollar);

        spin = findViewById(R.id.btnSpin);
        spin.setBackgroundColor(Color.TRANSPARENT);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMachine();
            }
        });
    }

    public void startMachine() {

        //TODO: Animation does not work correctly

        slot1.setImageResource(slotList.get(0).getImage());
        startAnimation(slot1);
        slot2.setImageResource(slotList.get(1).getImage());
        startAnimation(slot2);
        slot3.setImageResource(slotList.get(2).getImage());
        startAnimation(slot3);
        slot1.postDelayed(animationRunnable(slot1,1),500);
        slot3.postDelayed(createRunnable(),3000);
        slot3.postDelayed(waitForPopUp(),4000);
    }

    public void checkWin(){
        SecureRandom random = new SecureRandom();

        int id1;
        int stop = random.nextInt(3);
        slot1.setImageResource(slotList.get(stop).getImage());
        id1 = slotList.get(stop).getId();

        int id2;
        stop = random.nextInt(3);
        slot2.setImageResource(slotList.get(stop).getImage());
        id2 = slotList.get(stop).getId();

        int id3;
        stop = random.nextInt(3);
        slot3.setImageResource(slotList.get(stop).getImage());
        id3 = slotList.get(stop).getId();


        if (id1 == id2 && id2 == id3) {
            if (id1 == 3) { //When player has three dollar signs
                money = 230000;
                winString = "Gewonnen!";
                returnString = "Hauptgewinn! Du hast " + money + " gewonnen!";

            } else {
                money = 120000;
                winString = "Gewonnen!";
                returnString = "Drei gleiche Symbole! Du hast " + money + " gewonnen!";
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            money = 50000;
            winString = "Gewonnen!";
            returnString = "Zwei gleiche Symbole! Du hast " + money + " gewonnen!";
        }
        else{
            money = -20000;
            winString = "Verloren!";
            returnString = "Du hast " + money*-1 + " verloren!";
        }
    }

    private Runnable createRunnable() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checkWin();
            }
        };
        return runnable;
    }

    private Runnable waitForPopUp(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                openPopUp();
            }
        };
        return runnable;
    }

    private Runnable animationRunnable(final ImageView iv, final int i){
        Runnable r = new Runnable() {


            @Override
            public void run() {
                if (i >= 2){
                    throw new IllegalArgumentException();
                }
                slot1.setImageResource(slotList.get(i).getImage());
                startAnimation(slot1);
                slot2.setImageResource(slotList.get(i+1).getImage());
                startAnimation(slot2);
                slot3.setImageResource(slotList.get(i+2).getImage());
                startAnimation(slot3);
                startAnimation(iv);
            }
        }; return r;
    }

    private void openPopUp() {
        PopClass popClass = new PopClass();
        popClass.show(getSupportFragmentManager(), "alert");
        Bundle extras = new Bundle();
        extras.putInt("amount", money);
        extras.putString("returnString", returnString);
        extras.putString("winString", winString);
        popClass.setArguments(extras);
    }

    private Animation startAnimation(ImageView iv){
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(600);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(2);
        animation.setRepeatMode(1);
        iv.startAnimation(animation);
        return animation;
    }
}