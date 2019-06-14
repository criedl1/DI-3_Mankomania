package com.example.mankomania.slotmachine;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mankomania.R;
import com.example.mankomania.roulette.PopUp;

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
        slotList.add(dollar);
        slotList.add(star);
        slotList.add(seven);

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
        slot1.setImageResource(slotList.get(0).getImage());
        slot2.setImageResource(slotList.get(1).getImage());
        slot3.setImageResource(slotList.get(2).getImage());
        slot1.postDelayed(changePic(3, 0, 1), 400);
        slot1.postDelayed(changePic(2, 3, 0), 800);
        slot1.postDelayed(changePic(1, 2, 3), 1200);
        slot1.postDelayed(changePic(0, 1, 2), 1600);
        slot1.postDelayed(stopMachineAndCheckWin(), 2000);
        slot1.postDelayed(waitForPopUp(), 3000);

        /*hardcoded changePic, because i wanted to make sure, that the picture
        really changes every time, which isn't guaranteed when i solve it with
        random.*/
    }

    public void getStopIds() {
        SecureRandom random = new SecureRandom();
        id1 = slotList.get(random.nextInt(3)).getId();
        id2 = slotList.get(random.nextInt(3)).getId();
        id3 = slotList.get(random.nextInt(3)).getId();
        sml = new SlotMachineLogic(id1, id2, id3, this);
    }

    public void stopAnimation() {

        slot1.setImageResource(slotList.get(id1).getImage());

        slot2.setImageResource(slotList.get(id2).getImage());

        slot3.setImageResource(slotList.get(id3).getImage());
    }

    private Runnable stopMachineAndCheckWin() {

        return new Runnable() {
            @Override
            public void run() {
                getStopIds();
                stopAnimation();
                sml.checkWin();
            }
        };
    }

    private Runnable waitForPopUp() {
        return new Runnable() {
            @Override
            public void run() {
                openPopUp();
            }
        };
    }

    private Runnable changePic(final int s1, final int s2, final int s3) {
        return new Runnable() {

            @Override
            public void run() {
                slot1.setImageResource(slotList.get(s1).getImage());
                slot2.setImageResource(slotList.get(s2).getImage());
                slot3.setImageResource(slotList.get(s3).getImage());
            }
        };
    }

    private void openPopUp() {
        PopUp popUp = new PopUp();
        Bundle extras = new Bundle();
        extras.putString("popUpFor", "SlotMachineResult");
        extras.putInt("money", money);
        extras.putString("returnString", returnString);
        extras.putString("winString", winString);
        popUp.setArguments(extras);

        popUp.show(getSupportFragmentManager(), "alert");
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
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