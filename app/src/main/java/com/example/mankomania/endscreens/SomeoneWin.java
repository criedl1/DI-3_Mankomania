package com.example.mankomania.endscreens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mankomania.R;

public class SomeoneWin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someone_win);
        int player = getIntent().getIntExtra("Player",0);
        TextView txt = findViewById(R.id.txtSomeoneWin);
        txt.setText(String.format(getString(R.string.player_own), player));
    }
}
