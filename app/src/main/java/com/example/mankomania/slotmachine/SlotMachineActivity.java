package com.example.mankomania.slotmachine;

import android.graphics.Color;
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

public class SlotMachineActivity extends AppCompatActivity {

    private int money;
    private List<Symbol> slotList;
    private int id1;
    private int id2;
    private int id3;
    private ImageView slot1;
    private ImageView slot2;
    private ImageView slot3;
    private String returnString;
    private String winString;
    private SlotMachineLogic sml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        slotList = new ArrayList<>();

        Symbol cherry = new Symbol(R.drawable.cherry, 0);
        Symbol dollar = new Symbol(R.drawable.dollar, 1);
        Symbol star = new Symbol(R.drawable.star, 2);
        Symbol seven = new Symbol(R.drawable.sieben, 3);

        slot1 = findViewById(R.id.ivSlot1);
        slot2 = findViewById(R.id.ivSlot2);
        slot3 = findViewById(R.id.ivSlot3);

        slotList.add(cherry);
        slotList.add(star);
        slotList.add(seven);
        slotList.add(dollar);

        Button spin = findViewById(R.id.btnSpin);
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

    public void getStopIds(){
        SecureRandom random = new SecureRandom();
        id1 = slotList.get(random.nextInt(3)).getId();
        id2 = slotList.get(random.nextInt(3)).getId();
        id3 = slotList.get(random.nextInt(3)).getId();
        sml = new SlotMachineLogic(id1, id2, id3, this);
    }

    public void stopMachine() {

        slot1.setImageResource(slotList.get(id1).getImage());

        slot2.setImageResource(slotList.get(id2).getImage());

        slot3.setImageResource(slotList.get(id3).getImage());
    }

    private Runnable createRunnable() {

        return new Runnable() {
            @Override
            public void run() {
                getStopIds();
                stopMachine();
                sml.checkWin();
            }
        };
    }

    private Runnable waitForPopUp(){
        return new Runnable() {
            @Override
            public void run() {
                openPopUp();
            }
        };
    }

    private Runnable animationRunnable(final ImageView iv, final int i){
        return new Runnable() {


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
        };
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

    public void setMoney(int money){
        this.money = money;
    }

    public int getMoney(){
        return this.money;
    }

    public String getReturnString() {
        return returnString;
    }

    public void setReturnString(String returnString) {
        this.returnString = returnString;
    }

    public String getWinString() {
        return winString;
    }

    public void setWinString(String winString) {
        this.winString = winString;
    }
}