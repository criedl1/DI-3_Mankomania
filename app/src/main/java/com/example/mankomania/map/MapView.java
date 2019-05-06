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

import com.example.mankomania.Dice.Dice;
import com.example.mankomania.network.client.Client;
import com.example.mankomania.R;
import com.example.mankomania.Roulette.MainActivityRoulette;
import com.example.mankomania.Roulette.RotateActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import com.example.mankomania.network.NetworkConstants;


public class MapView extends AppCompatActivity {

    private ImageView imgview1;
    private ImageView imgview2;

    private int currentField = 0;

    private int currentPlayer = 1;
    Client client;

    int result;


    int[] allfields = { R.drawable.field_start, R.drawable.field_aktie1, R.drawable.field_lindwurm,
            R.drawable.field_lottery, R.drawable.field_casino, R.drawable.field_getsomemoney,
            R.drawable.field_alterplatz, R.drawable.field_aktie2, R.drawable.field_horserace,
            R.drawable.field_stadium, R.drawable.field_casino, R.drawable.field_alterplatz,
            R.drawable.field_horserace, R.drawable.field_lindwurm,R.drawable.field_klage,
            R.drawable.field_hotelsandwirth, R.drawable.field_getsomemoney, R.drawable.field_aktie2,
            R.drawable.field_woerthersee, R.drawable.field_horserace, R.drawable.field_casino,
            R.drawable.field_zoo, R.drawable.field_aktie3, R.drawable.field_lindwurm,
            R.drawable.field_woerthersee, R.drawable.field_klage, R.drawable.field_casino,
            R.drawable.field_seeparkhotel, R.drawable.field_plattenwirt, R.drawable.field_zoo,
            R.drawable.field_stadium, R.drawable.field_getsomemoney, R.drawable.field_aktie3,
            R.drawable.field_aktie1, R.drawable.field_casino, R.drawable.field_zoo
    };

      List<Player> players;



    //Screen Size
    private int screenWidth;

    //Images
    private ImageView[] figures = new ImageView[4];

    private TextView[] moneyFields = new TextView[4];


    private float field1;
    private float field2;
    private float field0;

    BroadcastReceiver resultReceiver;
    private int myPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        initButtons();

        // create Receiver
               resultReceiver = createBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                resultReceiver,
                new IntentFilter("client.update"));


        //Get Intent and start client
        Intent intent = getIntent();
        client = new Client(intent.getStringExtra("IP"),this);
        Log.i("INIT", "Start Client with IP "+intent.getStringExtra("IP"));
        client.start();

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

        //new Player
        players = new ArrayList<>();
        updateField();
    }

    //Broadcast Receiver to get Messages from the Client Thread
    private BroadcastReceiver createBroadcastReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                handleMessage(intent.getStringExtra("result"));
            }
        };
    }

    private void handleMessage(String message) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(message).getAsJsonObject();

        switch (jsonToString(jsonObject,NetworkConstants.OPERATION)) {
            case NetworkConstants.SEND_MONEY:
                printPositions("sendMoney");
                setMoneyUpdate(jsonObject);
                break;
            case NetworkConstants.SET_POSITION:
                setPositionUpdate(jsonObject);
                break;
            case NetworkConstants.SET_HYPO_AKTIE:
                setHypoAktieUpdate(jsonObject);
                break;
            case NetworkConstants.SET_STRABAG_AKTIE:
                setStrabagAktieUpdate(jsonObject);
                break;
            case NetworkConstants.SET_INFINEON_AKTIE:
                setInfineonAktieUpdate(jsonObject);
                break;
            case NetworkConstants.SET_CHEATER:
                setCheaterUpdate(jsonObject);
                break;
            case NetworkConstants.SET_LOTTO:
                setLottoUpdate(jsonObject);
                break;
            case NetworkConstants.SET_HOTEL:
                setHotelUpdate(jsonObject);
                break;
            case NetworkConstants.ROLL_DICE:
                rollDiceUpdate(jsonObject);
                break;
            case NetworkConstants.SPIN_WHEEL:
                spinWheelUpdate(jsonObject);
                break;
            case NetworkConstants.START_TURN:
                startTurnUpdate(jsonObject);
                break;
            case NetworkConstants.SET_PLAYER_COUNT:
                initPlayerCount(jsonObject);
                break;
            case "ROULETTERESULT":
                //TODO Should be in SPIN_WHEEL
                printPositions("ROULETTE");
                players.get(myPlayer).setMoney(players.get(myPlayer).getMoney()+jsonToInt(jsonObject,"result"));
                Log.i("MONEY", "Set Money of Player "+(myPlayer)+" to "+players.get(myPlayer).getMoney());
                client.setMoneyOnServer(myPlayer,players.get(myPlayer).getMoney());
                hideDice();
                break;
            case NetworkConstants.SET_ID:
                Log.i("INIT", "SET_ID: "+jsonObject.toString());
                this.myPlayer = jsonToInt(jsonObject, "ID");
                Log.i("INIT","Set my Player to: "+myPlayer);
                players.get(myPlayer).setIndex(myPlayer);
                break;
            default:
                break;
        }
    }

    private void hideDice() {
        findViewById(R.id.wuerfeln).setVisibility(View.INVISIBLE);
    }

    private void initPlayerCount(JsonObject jsonObject) {
        int count =jsonToInt(jsonObject,NetworkConstants.COUNT);
        Log.i("INIT", "Initialise "+ count+" players");
        for(int i = 0; i < count; i++){
            figures[i].setVisibility(View.VISIBLE);
            players.add(new Player(figures[i],moneyFields[i]));
        }
        closeWaitFragment();
    }


    private void closeWaitFragment() {
        Log.i("INIT","Closing wait Fragment");
        findViewById(R.id.waitContainer).setVisibility(View.INVISIBLE);
    }

    private void startTurnUpdate(JsonObject jsonObject) {
        findViewById(R.id.wuerfeln).setVisibility(View.VISIBLE);
        Toast.makeText(this,"Its your Turn now!", Toast.LENGTH_LONG).show();
    }

    private void setMoneyUpdate(JsonObject jsonObject) {
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int money = jsonToInt(jsonObject,NetworkConstants.MONEY);
        printPositions("setMoneyUpdateBefore");
        players.get(player).setMoney(money);
        printPositions("setMoneyUpdateAfter");
    }

    private void printPositions(String tag) {
        int index = 0;
        for (Player player : this.players) {
            Log.i("POSITION","Player "+index+": "+player.getCurrentField()+" ("+tag+")");
        }
    }

    private void setPositionUpdate(JsonObject jsonObject) {
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int position = jsonToInt(jsonObject,NetworkConstants.POSITION);

        //example
        Toast.makeText(this,"Pos "+player+position,Toast.LENGTH_LONG).show();

        //TODO Change Player Position on UI
    }
    private void setHypoAktieUpdate(JsonObject jsonObject) {
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);

        //example
        Toast.makeText(this,"Hypo "+player+count,Toast.LENGTH_LONG).show();

        //TODO Change Player-Aktie on UI
    }
    private void setStrabagAktieUpdate(JsonObject jsonObject) {
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);

        //example
        Toast.makeText(this,"Strabag "+player+count,Toast.LENGTH_LONG).show();

        //TODO Change Player-Aktie on UI
    }
    private void setInfineonAktieUpdate(JsonObject jsonObject) {
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        int count = jsonToInt(jsonObject,NetworkConstants.COUNT);

        //example
        Toast.makeText(this,"Infineon "+player+count,Toast.LENGTH_LONG).show();

        //TODO Change Player-Aktie on UI
    }
    private void setCheaterUpdate(JsonObject jsonObject) {
        int player =jsonToInt(jsonObject,NetworkConstants.PLAYER);
        boolean count = (jsonToInt(jsonObject,NetworkConstants.CHEATER)==1);

        //example
        Toast.makeText(this,"Cheater "+player+count,Toast.LENGTH_LONG).show();

        //TODO Cheater Action
    }
    private void setLottoUpdate(JsonObject jsonObject) {
        int amount = jsonToInt(jsonObject,NetworkConstants.AMOUNT);

        //example
        Toast.makeText(this,"Lotto "+amount,Toast.LENGTH_LONG).show();

        //TODO Lotto Actions
    }
    private void setHotelUpdate(JsonObject jsonObject) {
        int hotel =jsonToInt(jsonObject,NetworkConstants.HOTEL);
        int owner = jsonToInt(jsonObject,NetworkConstants.OWNER);

        //example
        Toast.makeText(this,"Hotel "+hotel+owner,Toast.LENGTH_LONG).show();

        //TODO Hotel Actions and UI
    }
    private void rollDiceUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int result = jsonToInt(jsonObject, NetworkConstants.RESULT);
        // Toast.makeText(this,"result of dice is: "+result, Toast.LENGTH_LONG).show();
        Dice fragment = ((Dice)getSupportFragmentManager().findFragmentById(R.id.diceContainer));
        this.result = result;
        if(fragment != null){
            fragment.showResult(result);
        }else{
            players.get(player).moveFields(result,allfields.length);
            players.get(player).getFigure().setVisibility(View.INVISIBLE);
            Toast.makeText(this,"Player "+ (player+1)+" diced "+ result, Toast.LENGTH_LONG).show();
        }
    }
    private void spinWheelUpdate(JsonObject jsonObject) {
        int player = jsonToInt(jsonObject, NetworkConstants.PLAYER);
        int result = jsonToInt(jsonObject, NetworkConstants.RESULT);

        //example
        Toast.makeText(this,"Roulette  "+player+result,Toast.LENGTH_LONG).show();

        //TODO Update UI for Roulette
    }

    private String jsonToString(JsonObject jsonObject, String key){
        return jsonObject.get(key).getAsString();
    }

    private int jsonToInt(JsonObject jsonObject, String key){
        return Integer.parseInt(jsonObject.get(key).getAsString());
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

        /*animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                switch(player.getCurrentField()){
                    case 4:     startRoulette();
                    case 20:    startRoulette();
                    case 26:    startRoulette();
                    case 34:    startRoulette();
                }
            }
        });*/

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void step1() {
        Player cPlayer = getCurrentPlayer();
        displayField(cPlayer.getCurrentField());
        movePlayerOut(cPlayer);
    }


    public void step2() {

        Player cPlayer = getCurrentPlayer();
        cPlayer.moveFields(this.result, allfields.length);
        movePlayerIn(cPlayer);
        displayField(cPlayer.getCurrentField());
        setCurrentPlayer(cPlayer);
    }

    public Player getCurrentPlayer() {
       //  return players.get(currentPlayer-1);
        return players.get(myPlayer);
    }

    public void setCurrentPlayer(Player player) {
        players.set(currentPlayer-1,player);
    }

    public void nextSideofMap(View view) {
        currentField += 2;
        currentField = currentField % allfields.length;
        updateField();
    }

    public void furtherSideofMap(View view) {
        currentField -= 2;
        if (currentField < 0) {
            currentField = allfields.length + currentField;
        }
        updateField();
    }

    public void displayField(int field) {
        currentField = field;

        if ((currentField & 1) != 0) {
            currentField--;
        }
        currentField = currentField % allfields.length;
        updateField();
    }


    public void updateField() {
        updatePlayers();
        imgview1.setImageResource(allfields[currentField]);
        imgview2.setImageResource(allfields[currentField+1]);
    }

    public void updatePlayers() {
        int index = 0;
        Log.i("DICEX","Update for currentField: "+currentField+"&"+(currentField+1));
        for (Player player : players) {
            Log.i("DICEX","Player: "+(index++)+" Position: "+player.getCurrentField());
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
        printPositions("before Roulette");
        Intent it = new Intent(this, MainActivityRoulette.class);
        startActivity(it);
    }

    public int changeMoney(){
        int temp = getCurrentPlayer().getMoney();
        return temp + RotateActivity.getMoney();
    }

    public void sendRollDice() {
        client.rollTheDice();
    }

    public void closeDiceFragment(View view) {
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.diceContainer)).commit();
        step1();
    }
}
