package com.example.beatpad;

import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

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
    private int sound10;
    private int sound11;
    private int sound12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean loaded = false;

        //Makes so media is default for volume control
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        String benis = "sound1";
        Resources res = this.getResources();
        int thing = res.getIdentifier(benis, "raw", this.getPackageName());

        //For modern phones
        //https://www.youtube.com/watch?v=fIWPSni7kUk
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            sp = new SoundPool.Builder()
                    .setMaxStreams(8)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        }
        sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);

        sound1 = sp.load(this,R.raw.sound25 , 1);
        sound2 = sp.load(this,R.raw.sound2 , 1);
        sound3 = sp.load(this,R.raw.sound3 , 1);
        sound4 = sp.load(this,R.raw.sound4 , 1);
        sound5 = sp.load(this, R.raw.sound5 , 1);
        sound6 = sp.load(this,R.raw.sound6 , 1);
        sound7 = sp.load(this,R.raw.sound7 , 1);
        sound8 = sp.load(this,R.raw.sound8, 1);
        sound9 = sp.load(this,R.raw.sound9 , 1);
        sound10 = sp.load(this,R.raw.sound22 , 1);
        sound11 = sp.load(this,R.raw.sound23 , 1);
        sound12 = sp.load(this,R.raw.sound24 , 1);


        //thanks, https://www.youtube.com/watch?v=on_OrrX7Nw4
        Spinner presets = (Spinner) findViewById(R.id.allPads);

        //Fills spinner with text
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.presets));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        presets.setAdapter(myAdapter);
        presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                           @Override
                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                               String text = parent.getItemAtPosition(position).toString();
                                               if (text.equals("Stock1")) {
                                                   sound1 = sp.load(MainActivity.this,R.raw.sound1 , 1);
                                                   sound2 = sp.load(MainActivity.this,R.raw.sound2 , 1);
                                                   sound3 = sp.load(MainActivity.this,R.raw.sound3 , 1);
                                                   sound4 = sp.load(MainActivity.this,R.raw.sound4 , 1);
                                                   sound5 = sp.load(MainActivity.this, R.raw.sound5 , 1);
                                                   sound6 = sp.load(MainActivity.this,R.raw.sound6 , 1);
                                                   sound7 = sp.load(MainActivity.this,R.raw.sound7 , 1);
                                                   sound8 = sp.load(MainActivity.this,R.raw.sound8, 1);
                                                   sound9 = sp.load(MainActivity.this,R.raw.sound9 , 1);
                                                   sound10 = sp.load(MainActivity.this,R.raw.sound00 , 1);
                                                   sound11 = sp.load(MainActivity.this,R.raw.sound25 , 1);
                                                   sound12 = sp.load(MainActivity.this,R.raw.sound24 , 1);
                                               } else if (text.equals("Stock2")) {
                                                   sound1 = sp.load(MainActivity.this,R.raw.sound13 , 1);
                                                   sound2 = sp.load(MainActivity.this,R.raw.sound14, 1);
                                                   sound3 = sp.load(MainActivity.this,R.raw.sound15 , 1);
                                                   sound4 = sp.load(MainActivity.this,R.raw.sound16 , 1);
                                                   sound5 = sp.load(MainActivity.this, R.raw.sound17 , 1);
                                                   sound6 = sp.load(MainActivity.this,R.raw.sound18 , 1);
                                                   sound7 = sp.load(MainActivity.this,R.raw.sound19 , 1);
                                                   sound8 = sp.load(MainActivity.this,R.raw.sound20, 1);
                                                   sound9 = sp.load(MainActivity.this,R.raw.sound21 , 1);
                                                   sound10 = sp.load(MainActivity.this,R.raw.sound22 , 1);
                                                   sound11 = sp.load(MainActivity.this,R.raw.sound23 , 1);
                                                   sound12 = sp.load(MainActivity.this,R.raw.sound24 , 1);
                                               }
                                           }

                                           @Override
                                           public void onNothingSelected(AdapterView<?> parent) {

                                           }
                                       });


        //Makes ontouch instead of onclick
        //Thanks, https://stackoverflow.com/questions/4597513/onpress-onrelease-in-android
        //Debugging stuff http://blog.vogella.com/2011/06/27/android-soundpool-how-to-check-if-sound-file-is-loaded/
        final Button pad1 = findViewById(R.id.pad1);
        pad1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound1, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad2 = findViewById(R.id.pad2);
        pad2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound2, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad3 = findViewById(R.id.pad3);
        pad3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound3, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad4 = findViewById(R.id.pad4);
        pad4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound4, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad5 = findViewById(R.id.pad5);
        pad5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound5, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad6 = findViewById(R.id.pad6);
        pad6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound6, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad7 = findViewById(R.id.pad7);
        pad7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound7, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad8 = findViewById(R.id.pad8);
        pad8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound8, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad9 = findViewById(R.id.pad9);
        pad9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound9, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad10 = findViewById(R.id.pad10);
        pad10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound10, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad11 = findViewById(R.id.pad11);
        pad11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound11, 1, 1,0,0,1);
                }
                return true;
            }
        });

        final Button pad12 = findViewById(R.id.pad12);
        pad12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN) {
                    sp.play(sound12, 1, 1,0,0,1);
                }
                return true;
            }
        });
    }

}
