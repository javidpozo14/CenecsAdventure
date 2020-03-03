package com.jcarlosalarconp.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LevelsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_screen);
    }

    public void play(View view) {
        Intent service = new Intent(this, MyService.class);
        startService(service);
        Intent intent= new Intent(this, AndroidLauncher.class);
        startActivity(intent);
    }
}
