package com.example.beatpad;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

//Used this link for ui layout
//https://www.youtube.com/watch?v=BTc9Xr1TtQk&fbclid=IwAR2R50B1hWRK1-0Dg4S_HWJF1yNnAgy9299xeWXIzexZqLNsshaSR1XJ118

public class MainActivity extends AppCompatActivity {

    //String to store both read and write permissions
    private String[] Permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    //Thanks, https://www.youtube.com/watch?v=SMrB97JuIoM
    //Thanks, https://www.youtube.com/watch?v=iqFRdjYqGPo
    //integer to store permissions
    private int Permission_All = 1;

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

    Button toCustom;

    private static final String TAG = "MainActivity";
    public static final String root = "CustomPads";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"Entered onCreate" );

        //Thanks, https://www.youtube.com/watch?v=SMrB97JuIoM
        //Thanks, https://www.youtube.com/watch?v=iqFRdjYqGPo
        if (!hasPermissions(this, Permissions)) {
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        } else {
            File f = new File(Environment.getExternalStorageDirectory(), root);
            //Makes file directory to store custom beat tracks
            //Only if doesn't exist already
            //Thanks, https://stackoverflow.com/questions/24781213/how-to-create-a-folder-in-android-external-storage-directory
            if (!f.exists()) {
                f.mkdirs();
            }
        }

        Log.d(TAG,"Checked Permissions" );
        toCustom = (Button) findViewById(R.id.toCustom);
        toCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomMaker.class);
                startActivity(intent);
            }
        });


        //Makes so media is default for volume control
        setVolumeControlStream(AudioManager.STREAM_MUSIC);


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


        Log.d(TAG,"Loaded soundpool" );
        //thanks, https://www.youtube.com/watch?v=on_OrrX7Nw4
        Spinner presets = (Spinner) findViewById(R.id.allPads);

        //ArrayList to fill spinner with
        ArrayList<String> loadouts = new ArrayList<>();
        loadouts.add("Stock1");
        loadouts.add("Stock2");

        //get all possible folders for loadouts and append their names to the loadouts spinner
        //thanks, https://stackoverflow.com/questions/8646984/how-to-list-files-in-an-android-directory/53494368

        String path = Environment.getExternalStorageDirectory().toString() + "/" + root;
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            loadouts.add(files[i].getName());
            Log.d("Files", "FileName:" + files[i].getName());
        }


        //Fills spinner with text
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, loadouts);
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
                                               } else {
                                                   String toOpen = Environment.getExternalStorageDirectory().toString() + "/" + root + "/" + text;
                                                   Log.d("Files", "Path: " + toOpen);
                                                   try {
                                                       sound1 = sp.load(toOpen + "/sound1.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound1 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound2 = sp.load(toOpen + "/sound2.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound2 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound3 = sp.load(toOpen + "/sound3.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound3 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound4 = sp.load(toOpen + "/sound4.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound4 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound5 = sp.load(toOpen + "/sound5.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound5 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound6 = sp.load(toOpen + "/sound6.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound6 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound7 = sp.load(toOpen + "/sound7.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound7 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound8 = sp.load(toOpen + "/sound8.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound8 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound9 = sp.load(toOpen + "/sound9.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound9 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound10 = sp.load(toOpen + "/sound10.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound10 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound11 = sp.load(toOpen + "/sound11.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound11 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
                                                   try {
                                                       sound12 = sp.load(toOpen + "/sound12.3gp", 1);
                                                   } catch (Exception e) {
                                                       sound12 = sp.load(MainActivity.this,R.raw.sound8 , 1);
                                                   }
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

    /**
     * @param context
     * @param permissions string containing the permissions
     * @return true if all permissions are satisfied
     */
    public static boolean hasPermissions(Context context, String... permissions){
        //Checks build version
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null && permissions!=null){
            //Checks each permission individually
            for(String permission: permissions) {
                if(ActivityCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
