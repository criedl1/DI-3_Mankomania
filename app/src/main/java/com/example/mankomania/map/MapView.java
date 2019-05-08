package com.example.mankomania.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mankomania.R;
import com.example.mankomania.dice.Dice;
import com.example.mankomania.roulette.MainActivityRoulette;


public class MapView extends AppCompatActivity {

    private ImageView imgview1;
    private ImageView imgview2;



    //Screen Size
    private int screenWidth;

    //Images
    private ImageView[] figures = new ImageView[4];

    private TextView[] moneyFields = new TextView[4];


    private float field1;
    private float field2;
    private float field0;

    BroadcastReceiver resultReceiver;

    private GameController gameController;
    private int currentField = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        initButtons();



        //Get Intent and start client
        Intent intent = getIntent();

        figures[0] = findViewById(R.id.figure1);
        figures[1] = findViewById(R.id.figure2);
        figures[2] = findViewById(R.id.figure3);
        figures[3] = findViewById(R.id.figure4);

        moneyFields[0] = findViewById(R.id.currentmoney);
        moneyFields[1] = findViewById(R.id.currentmoney2);
        moneyFields[2] = findViewById(R.id.currentmoney3);
        moneyFields[3] = findViewById(R.id.currentmoney4);


        //Position on fields for figures
        field1 = 300;
        field2 = 1000;
        field0 = -50;

        //Get screen size
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;

        //Start Position of figures
        figures[0].setX(field1);
        figures[0].setY(60);
        figures[1].setX(field1);
        figures[1].setY(300);
        figures[2].setX(field1);
        figures[2].setY(510);
        figures[3].setX(field1);
        figures[3].setY(710);

        // create Receiver
        resultReceiver = createBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                resultReceiver,
                new IntentFilter("client.update"));

        this.gameController = new GameController(intent.getStringExtra("IP"),this);

        this.gameController.startClient();
    }

    //Broadcast Receiver to get Messages from the Client Thread
    private BroadcastReceiver createBroadcastReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("JONTEST","MapVIew: "+intent.getStringExtra("result"));
                gameController.handleMessage(intent.getStringExtra("result"));
            }
        };
    }


    private void closeWaitFragment() {
        Log.i("INITJS","Closing wait Fragment");
        findViewById(R.id.waitContainer).setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onDestroy() {
        if (resultReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(
                    resultReceiver);
        }
        super.onDestroy();
    }

    public void movePlayerOut(final Player player){
        float distance;
            distance = screenWidth;

        ObjectAnimator animation = ObjectAnimator.ofFloat(player.getFigure(), "translationX", distance);
        animation.setDuration(5000);
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                step2();
            }
        });
        animation.start();

    }

    public void movePlayerIn(final Player player) {
        float distance;
            distance = field1 - field0;

        player.getFigure().setX(field0);
        ObjectAnimator animation = ObjectAnimator.ofFloat(player.getFigure(), "translationX", distance);
        animation.setDuration(5000);
        animation.start();
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                super.onAnimationEnd(animation);
                startRoulette();
            }
        });
    }

    private void initButtons() {
        imgview1 =  findViewById(R.id.imageViewStart);
        imgview2 =  findViewById(R.id.imageView2);

        ImageView wuerfeln =  findViewById(R.id.wuerfeln); // button fürs würfeln
        wuerfeln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiceFragment();
            }
        });
    }

    public void showDiceFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Dice fragment = new Dice();
        transaction.add(R.id.diceContainer,fragment);
        transaction.commit();

    }

    public void step1() {
        Player cPlayer = gameController.currentPlayer();
        displayField(cPlayer.getCurrentField());
        movePlayerOut(cPlayer);
    }


    public void step2() {

        Player cPlayer = gameController.currentPlayer();
        movePlayerIn(cPlayer);
        displayField(cPlayer.getCurrentField());
    }

    public void nextSideofMap(View view) {
        currentField += 2;
        currentField = currentField % GameController.allfields.length;
        updateField();
    }

    public void furtherSideofMap(View view) {
        currentField -= 2;
        if (currentField < 0) {
            currentField = GameController.allfields.length + currentField;
        }
        updateField();
    }

    public void displayField(int field) {
        currentField = field;

        if ((currentField & 1) != 0) {
            currentField--;
        }
        currentField = currentField % GameController.allfields.length;
        updateField();
    }


    public void updateField() {
        updatePlayers();
        imgview1.setImageResource(GameController.allfields[currentField]);
        imgview2.setImageResource(GameController.allfields[currentField+1]);
    }

    public void updatePlayers() {
        // Log.i("DICEX","Update for currentField: "+currentField+"&"+(currentField+1));
        for (Player player : gameController.players) {
            // Log.i("DICEX","Player: "+(index++)+" Position: "+player.getCurrentField());
            if(player.getCurrentField() == currentField) {
                player.getFigure().setX(field1);
                player.getFigure().setVisibility(View.VISIBLE);
            }else if(player.getCurrentField() == currentField+1) {
                player.getFigure().setX(field2);
                player.getFigure().setVisibility(View.VISIBLE);
            }else {
                player.getFigure().setVisibility(View.INVISIBLE);
            }
        }
    }

    public void startRoulette(){
        Intent it = new Intent(this, MainActivityRoulette.class);
        startActivity(it);
    }


    public void sendRollDice() {
        gameController.rollTheDice();
    }

    public void closeDiceFragment(View view) {
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.diceContainer)).commit();
    }

    public void showMyDiceResult(int outcome) {
        Dice fragment = ((Dice)getSupportFragmentManager().findFragmentById(R.id.diceContainer));
        if(fragment != null) {
            fragment.showResult(outcome);
        }else{
            //should not happen because it is my turn
            throw new IllegalStateException();
        }
    }

    public void showSomeonesDiceResult(int player, int outcome) {
        Toast.makeText(this,"Player "+ (player+1)+" diced "+ outcome, Toast.LENGTH_LONG).show();
    }

    public void initPlayerFields() {
        for (int i = 0; i < this.gameController.players.size(); i++) {
            gameController.players.get(i).initFields(figures[i],moneyFields[i]);
        }
        updatePlayers();
        closeWaitFragment();
    }

    public void startMyTurn() {
        ImageView wuerfeln =  findViewById(R.id.wuerfeln); // button fürs würfeln
        wuerfeln.setVisibility(View.VISIBLE);
    }

    public void endMyTurn() {
        ImageView wuerfeln =  findViewById(R.id.wuerfeln); // button fürs würfeln
        wuerfeln.setVisibility(View.INVISIBLE);
    }
}
