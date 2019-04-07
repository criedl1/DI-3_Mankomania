package com.example.mankomania;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;


public class start_view extends AppCompatActivity {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;


    //Screen Size
    private int screenWidth;
    private int screenHeight;

    //Image
    private ImageView figure1;
    private ImageView figure2;
    private ImageView figure3;
    private ImageView figure4;
    //Position
    private float figure1X;
    private float figure1Y;
    private float figure2X;
    private float figure2Y;
    private float figure3X;
    private float figure3Y;
    private float figure4X;
    private float figure4Y;

    private float field1;
    private float field2;

    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);
        initButtons();
        figure1 = (ImageView)findViewById(R.id.figure1);
        figure2 = (ImageView)findViewById(R.id.figure2);
        figure3 = (ImageView)findViewById(R.id.figure3);
        figure4 = (ImageView)findViewById(R.id.figure4);

        field1 = 300;
        field2 = 1000;

        //Get screen size
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        //Move to out of screen

        figure1.setX(field1);
        figure1.setY(60);
        figure2.setX(field1);
        figure2.setY(300);
        figure3.setX(field2);
        figure3.setY(470);
        figure4.setX(field1);
        figure4.setY(710);

        player1 = new Player(figure1);


  /*  timer.schedule(new TimerTask() {
            @Override
            public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                changePosition();
                }
            });
}
        },0,50);
*/
    }
    public void movePlayer(Player player, int fields){
        player.setCurrentField(player.getCurrentField()+fields);
      /*  while(player.getFigure().getX() < field2) {
            player.getFigure().setX(player.getFigure().getX()+10);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.getFigure().invalidate();
        }
        player.getFigure().setX(field2);*/
        float distance = field2-player.getFigure().getX()+player.getFigure().getWidth();

        ObjectAnimator animation = ObjectAnimator.ofFloat(player.getFigure(), "translationX", distance);
        animation.setDuration(2000);
        animation.start();


    }

    public void changePosition(){
        //Speed
        figure1X +=30;
        figure2X +=30;
        figure3X +=30;
        figure4X +=30;
        if(figure1.getX() > screenWidth) {
        figure1X = -100.0f;

        }
        figure1.setX(figure1X);
        if(figure2.getX() > screenWidth) {
            figure2X = -100.0f;
        }
        figure2.setX(figure2X);
        if(figure3.getX() > screenWidth) {
            figure3X = -100.0f;
        }
        figure3.setX(figure3X);
        if(figure4.getX() > screenWidth) {
            figure4X = -100.0f;
        }
        figure4.setX(figure4X);
        }





 private void initButtons() {
        Button nextButton = (Button) findViewById(R.id.next1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(start_view.this, map_view2.class));
            }
        });

    Button moveTest = (Button) findViewById(R.id.moveTest);
        moveTest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          movePlayer(player1,1);
        }
    });
}


}
