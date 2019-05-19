package com.example.mankomania.slotmachine;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    Symbol cherry = new Symbol(R.drawable.cherry, 1);
    Symbol dollar = new Symbol(R.drawable.dollar, 2);
    Symbol star = new Symbol(R.drawable.star, 3);
    Symbol seven = new Symbol(R.drawable.sieben, 7);
    ImageView slot1;
    ImageView slot2;
    ImageView slot3;
    Button spin;
    String returnString;

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

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < slotList.size(); i++) {
                slot1.setImageResource(slotList.get(i).getImage());
            }
            for (int i = 1; i < slotList.size(); i++) {
                slot2.setImageResource(slotList.get(i).getImage());
            }
            for (int i = 2; i < slotList.size(); i++) {
                slot3.setImageResource(slotList.get(i).getImage());
            }
        }

        checkWin();
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
                returnString = "Du hast " + money + " gewonnen!";
                openPopUp();

            } else {
                money = 120000;
                returnString = "Du hast " + money + " gewonnen!";
                openPopUp();
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            money = 50000;
            returnString = "Du hast " + money + " gewonnen!";
            openPopUp();
        }
        else{
            money = -20000;
            returnString = "Du hast " + money*-1 + " verloren!";
            openPopUp();
        }
    }

    private void openPopUp() {
        PopClass popClass = new PopClass();
        popClass.show(getSupportFragmentManager(), "alert");
        Bundle extras = new Bundle();
        extras.putInt("amount", money);
        extras.putString("returnString", returnString);
        popClass.setArguments(extras);
    }
}

