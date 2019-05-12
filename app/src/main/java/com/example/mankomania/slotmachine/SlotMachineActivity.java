package com.example.mankomania.slotmachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mankomania.R;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SlotMachineActivity extends AppCompatActivity {

    public static int amount;
    private List<Symbol> slot1;
    private List<Symbol> slot2;
    private List<Symbol> slot3;
    ImageView white1;
    ImageView white2;
    ImageView white3;
    ImageView star1;
    ImageView star2;
    ImageView star3;
    ImageView cherry1;
    ImageView cherry2;
    ImageView cherry3;
    ImageView seven1;
    ImageView seven2;
    ImageView seven3;
    ImageView dollar1;
    ImageView dollar2;
    ImageView dollar3;
    Button start;
    String returnString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        setUpSlotMachine();

        white1 = findViewById(R.id.ivWhite1);
        white2 = findViewById(R.id.ivWhite2);
        white3 = findViewById(R.id.ivWhite3);
        star1 = findViewById(R.id.ivStar1);
        star2 = findViewById(R.id.ivStar2);
        star3 = findViewById(R.id.ivStar3);
        cherry1 = findViewById(R.id.ivCherry1);
        cherry2 = findViewById(R.id.ivCherry2);
        cherry3 = findViewById(R.id.ivCherry3);
        seven1 = findViewById(R.id.ivSeven1);
        seven2 = findViewById(R.id.ivSeven2);
        seven3 = findViewById(R.id.ivSeven3);
        dollar1 = findViewById(R.id.ivDollar1);
        dollar2 = findViewById(R.id.ivDollar2);
        dollar3 = findViewById(R.id.ivDollar3);
        start = findViewById(R.id.btnSpin);
        Button spin = findViewById(R.id.btnSpin);

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMachine();
            }
        });
    }

    private void setUpSlotMachine() {
        Symbol Star1 = new Symbol(star1, 1);
        Symbol Star2 = new Symbol(star2, 1);
        Symbol Star3 = new Symbol(star3, 1);
        Symbol Cherry1 = new Symbol(cherry1, 2);
        Symbol Cherry2 = new Symbol(cherry2, 2);
        Symbol Cherry3 = new Symbol(cherry3, 2);
        Symbol Seven1 = new Symbol(seven1, 7);
        Symbol Seven2 = new Symbol(seven2, 7);
        Symbol Seven3 = new Symbol(seven3, 7);
        Symbol Dollar1 = new Symbol(dollar1, 3);
        Symbol Dollar2 = new Symbol(dollar2, 3);
        Symbol Dollar3 = new Symbol(dollar3, 3);

        slot1.add(Star1);
        slot1.add(Cherry1);
        slot1.add(Seven1);
        slot1.add(Dollar1);
        slot2.add(Star2);
        slot2.add(Cherry2);
        slot2.add(Seven2);
        slot2.add(Dollar2);
        slot3.add(Star3);
        slot3.add(Cherry3);
        slot3.add(Seven3);
        slot3.add(Dollar3);

        for (int i = 0; i < slot1.size(); i++) {
            for (Symbol s : slot1) {
                s.getImage().setVisibility(View.INVISIBLE);
            }
        }
        for (int i = 0; i < slot2.size(); i++) {
            for (Symbol s : slot2) {
                s.getImage().setVisibility(View.INVISIBLE);
            }
        }
        for (int i = 0; i < slot3.size(); i++) {
            for (Symbol s : slot3) {
                s.getImage().setVisibility(View.INVISIBLE);
            }
        }
    }

    protected void startMachine() {
        SecureRandom random = new SecureRandom();

        try {
            for (int i = 0; i < 10; i++) {    //every Symbol blinks ten times
                for (Symbol s : slot1) {
                    s.getImage().setVisibility(View.VISIBLE);
                    TimeUnit.SECONDS.sleep(1);
                    s.getImage().setVisibility(View.INVISIBLE);
                }
            }
            for (int i = 0; i < 7; i++) {
                for (Symbol s : slot2) {
                    s.getImage().setVisibility(View.VISIBLE);
                    TimeUnit.SECONDS.sleep(1);
                    s.getImage().setVisibility(View.INVISIBLE);
                }
            }
            for (int i = 0; i < 5; i++) {
                for (Symbol s : slot3) {
                    s.getImage().setVisibility(View.VISIBLE);
                    TimeUnit.SECONDS.sleep(1);
                    s.getImage().setVisibility(View.INVISIBLE);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int id1;
        int stop = random.nextInt(3);
        slot1.get(stop).getImage().setVisibility(View.VISIBLE);
        id1 = slot1.get(stop).getId();

        int id2;
        stop = random.nextInt(3);
        slot2.get(stop).getImage().setVisibility(View.VISIBLE);
        id2 = slot2.get(stop).getId();

        int id3;
        stop = random.nextInt(3);
        slot2.get(stop).getImage().setVisibility(View.VISIBLE);
        id3 = slot3.get(stop).getId();


        if (id1 == id2 && id2 == id3) {
            if (id1 == 3) { //When player has three dollar signs
                amount = 230000;
                returnString = getString(R.string.slot_win);
                openPopUp();

            } else {
                amount = 120000;
                returnString = getString(R.string.slot_win);
                openPopUp();
            }
        } else if (id1 == id2 || id2 == id3 || id1 == id3) {
            amount = 50000;
            returnString = getString(R.string.slot_win);
            openPopUp();
        }
        else{
            amount = -20000;
            returnString = getString(R.string.slot_lost);
            openPopUp();
        }
    }

    private void openPopUp() {
        PopClass popClass = new PopClass();
        popClass.show(getSupportFragmentManager(), "alert");
        Bundle extras = new Bundle();
        extras.putInt("amount", amount);
        extras.putString("returnString", returnString);
        popClass.setArguments(extras);
    }
}

