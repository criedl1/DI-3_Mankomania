package com.example.mankomania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class map_view3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view3);

        goBack();
        gotoNext();
    }

    private void goBack() {
        Button backButton = (Button) findViewById(R.id.back3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(map_view3.this, map_view2.class));
            }
        });
    }

    private void gotoNext() {
        Button nextButton = (Button) findViewById(R.id.next3);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(map_view3.this, map_view4.class));
            }
        });
    }
}
