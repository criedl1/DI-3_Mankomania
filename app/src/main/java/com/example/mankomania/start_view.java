package com.example.mankomania;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mankomania.Network.Client.Client;


public class start_view extends AppCompatActivity {

    private static Button next;
    private static Button back;
    private static ImageView imgview1;
    private static ImageView imgview2;
    private int currentField=0;
    private int currentPlayer = 1;
    private int numberofplayers = 2;
    private static TextView money;
    Client client;

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

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;


    //Screen Size
    private int screenWidth;
    private int screenHeight;

    //Images
    private ImageView figure1;
    private ImageView figure2;
    private ImageView figure3;
    private ImageView figure4;

    //Position
    private float figure1X;
    private float figure2X;
    private float figure3X;
    private float figure4X;

    private float field1;
    private float field2;
    private float field0;

    //Initialize Class
  //  private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);
        initButtons();
        money = (TextView)findViewById(R.id.currentmoney);
        //Get Intent and start client
        Intent intent = getIntent();
        client = new Client(intent.getStringExtra("IP"));
        client.start();


        figure1 = (ImageView)findViewById(R.id.figure1);
        figure2 = (ImageView)findViewById(R.id.figure2);
        figure3 = (ImageView)findViewById(R.id.figure3);
        figure4 = (ImageView)findViewById(R.id.figure4);

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
        screenHeight = size.y;

        //Start Position of figures

        figure1.setX(field1);
        figure1.setY(60);
        figure2.setX(field1);
        figure2.setY(300);
        figure3.setX(field1);
        figure3.setY(510);
        figure4.setX(field1);
        figure4.setY(710);

        //new Player
        player1 = new Player(figure1,money);
        player2 = new Player(figure2,money);
        player3 = new Player(figure3,money);
        player4 = new Player(figure4,money);

        updateField();


    }
    public void movePlayerOut(Player player){
        float distance;
        boolean playeronleft= (player.getCurrentField() & 1) == 0;
        if(playeronleft) {
            distance = screenWidth;
        } else {
            distance = screenWidth;
        }
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

    public void movePlayerIn(Player player){
        float distance;
        boolean playeronleft= (player.getCurrentField() & 1) == 0;
        if(playeronleft) {
            distance = field1-field0;
        } else {
            distance =field2-field0;
        }
        player.getFigure().setX(field0);
        ObjectAnimator animation = ObjectAnimator.ofFloat(player.getFigure(), "translationX", distance);
        animation.setDuration(5000);
        animation.start();
    }


 private void initButtons() {
     imgview1 = (ImageView) findViewById(R.id.imageViewStart);
     imgview2 = (ImageView) findViewById(R.id.imageView2);
     next = (Button)findViewById(R.id.next1);
     back = (Button)findViewById(R.id.back1);


     next.setOnClickListener(
             new View.OnClickListener(){
                 @Override
                 public void onClick(View view) {
                    nextSideofMap();
                 }
             }
     );
     back.setOnClickListener(
             new View.OnClickListener(){
                 @Override
                 public void onClick(View view) {
                  furtherSideofMap();
             } }
     );

    Button moveTest = (Button) findViewById(R.id.moveTest);
        moveTest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        }
    });

        Button setMoney = (Button)findViewById(R.id.setmoney);
        setMoney.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                player1.addMoney(12345);


            }

        });

        Button würfeln = (Button)findViewById(R.id.würfeln);
        würfeln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               step1();
            }
        });

}
    public void step1() {
        Player cPlayer = getCurrentPlayer();
        displayField(cPlayer.getCurrentField());
        movePlayerOut(cPlayer);
    }
    public void step2() {
        int würfelergebnis = throwDie()+throwDie();
        Player cPlayer = getCurrentPlayer();
        cPlayer.moveFields(würfelergebnis, allfields.length);
        movePlayerIn(cPlayer);
        displayField(cPlayer.getCurrentField());
        setCurrentPlayer(cPlayer);
        nextPlayer();
    }
    public int throwDie()
    {
        return (int)(Math.random() * 6) + 1;
    }

    public void nextPlayer() {
     currentPlayer++;
     if(currentPlayer > numberofplayers) {
         currentPlayer = 1;
     }
    }
    public Player getCurrentPlayer() {
     if(currentPlayer == 1) return player1;
        if(currentPlayer == 2) return player2;
        if(currentPlayer == 3) return player3;
        if(currentPlayer == 4) return player4;
        return null;
    }
    public void setCurrentPlayer(Player player) {
        if(currentPlayer == 1) player1=player;
        if(currentPlayer == 2) player2=player;
        if(currentPlayer == 3) player3=player;
        if(currentPlayer == 4) player4=player;
    }


    public void nextSideofMap() {
        currentField+=2;
        currentField = currentField % allfields.length;
        updateField();
    }

    public void furtherSideofMap() {
        currentField-=2;
        if(currentField >= 0){

        } else {
            currentField=allfields.length+currentField;
        }
       updateField();
    }

    public void displayField(int field) {
        currentField = field;
        if ( (currentField & 1) != 0 ) { currentField--;}

        currentField = currentField % allfields.length;
        updateField();
    }

    public void updateField() {
        updatePlayers();
        imgview1.setImageResource(allfields[currentField]);
        imgview2.setImageResource(allfields[currentField+1]);
    }
    public void updatePlayers() {
        if(player1.getCurrentField() == currentField) {
            player1.getFigure().setX(field1);
            player1.getFigure().setVisibility(View.VISIBLE);
        }else if(player1.getCurrentField() == currentField+1) {
            player1.getFigure().setX(field2);
            player1.getFigure().setVisibility(View.VISIBLE);
        }
        else {
            player1.getFigure().setVisibility(View.INVISIBLE);
        }
        if(player2.getCurrentField() == currentField) {
            player2.getFigure().setX(field1);
            player2.getFigure().setVisibility(View.VISIBLE);
        }else if(player2.getCurrentField() == currentField+1) {
            player2.getFigure().setX(field2);
            player2.getFigure().setVisibility(View.VISIBLE);
        }
        else {
            player2.getFigure().setVisibility(View.INVISIBLE);
        }
        if(player3.getCurrentField() == currentField) {
            player3.getFigure().setX(field1);
            player3.getFigure().setVisibility(View.VISIBLE);
        }else if(player3.getCurrentField() == currentField+1) {
            player3.getFigure().setX(field2);
            player3.getFigure().setVisibility(View.VISIBLE);
        }
        else {
            player3.getFigure().setVisibility(View.INVISIBLE);
        }
        if(player4.getCurrentField() == currentField) {
            player4.getFigure().setX(field1);
            player4.getFigure().setVisibility(View.VISIBLE);
        }else if(player4.getCurrentField() == currentField+1) {
            player4.getFigure().setX(field2);
            player4.getFigure().setVisibility(View.VISIBLE);
        }
        else {
            player4.getFigure().setVisibility(View.INVISIBLE);
        }

    }
}
