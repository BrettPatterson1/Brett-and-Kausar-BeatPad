package com.example.beatpad;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//Used this link for ui layout
//https://www.youtube.com/watch?v=BTc9Xr1TtQk&fbclid=IwAR2R50B1hWRK1-0Dg4S_HWJF1yNnAgy9299xeWXIzexZqLNsshaSR1XJ118

public class MainActivity extends AppCompatActivity {

    private SoundPool sp;
    private int sound1;
    private int sound2;
    private int sound3;
    private int sound4;
    private int sound5;
    private int sound6;
    private int sound7;
    private int sound8;
    private int sound9;
    private int sound00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        sound1 = sp.load(getApplicationContext(),R.raw.sound1 , 1);
        sound2 = sp.load(getApplicationContext(),R.raw.sound2 , 1);
        sound3 = sp.load(getApplicationContext(),R.raw.sound3 , 1);
        sound4 = sp.load(getApplicationContext(),R.raw.sound4 , 1);
        sound5 = sp.load(getApplicationContext(),R.raw.sound5 , 1);
        sound6 = sp.load(getApplicationContext(),R.raw.sound6 , 1);
        sound7 = sp.load(getApplicationContext(),R.raw.sound7 , 1);
        sound8 = sp.load(getApplicationContext(),R.raw.sound8 , 1);
        sound9 = sp.load(getApplicationContext(),R.raw.sound9 , 1);
        sound00 = sp.load(getApplicationContext(),R.raw.sound00 , 1);

        Spinner allPads = (Spinner) findViewById(R.id.allPads);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allPads.setAdapter(myAdapter);
    }

    public void pad1(View view) {
        sp.play(sound1, 1.0f, 1.0f,0,0,10f);
    }

    public void pad2(View view) {
        sp.play(sound2, 1.0f, 1.0f,0,0,10f);
    }

    public void pad3(View view) {
        sp.play(sound3, 1.0f, 1.0f,0,0,10f);
    }

    public void pad4(View view) {
        sp.play(sound4, 1.0f, 1.0f,0,0,10f);
    }

    public void pad5(View view) {
        sp.play(sound5, 1.0f, 1.0f,0,0,10f);
    }

    public void pad6(View view) {
        sp.play(sound6, 1.0f, 1.0f,0,0,10f);
    }

    public void pad7(View view) {
        sp.play(sound7, 1.0f, 1.0f,0,0,10f);
    }

    public void pad8(View view) {
        sp.play(sound8, 1.0f, 1.0f,0,0,10f);
    }

    public void pad9(View view) {
        sp.play(sound9, 1.0f, 1.0f,0,0,10f);
    }

    public void pad10(View view) {

    }

    public void pad11(View view) {

    }

    public void pad12(View view) {

    }
}
