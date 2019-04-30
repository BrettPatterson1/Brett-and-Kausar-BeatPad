package com.example.beatpad;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class CustomMaker extends AppCompatActivity {

    //String to store both read and write permissions
    private String[] Permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    //Thanks, https://www.youtube.com/watch?v=SMrB97JuIoM
    //Thanks, https://www.youtube.com/watch?v=iqFRdjYqGPo
    //integer to store permissions
    private int Permission_All = 1;
    Button save;
    Button record;
    Button stop;
    EditText name;

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

    private String dir;
    MediaRecorder myAudioRecorder;

    private SoundPool sp;

    int lastPressed = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_maker);

        //Thanks, https://www.youtube.com/watch?v=SMrB97JuIoM
        //Thanks, https://www.youtube.com/watch?v=iqFRdjYqGPo
        if (!hasPermissions(this, Permissions)) {
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }



        //Link views to variables
        save = (Button) findViewById(R.id.save);
        record = (Button) findViewById(R.id.record);
        stop = (Button) findViewById(R.id.stop);
        name = (EditText) findViewById(R.id.name);

        stop.setEnabled(false);

        sp = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);

        sound1 = sp.load(this,R.raw.sound9 , 1);
        sound2 = sp.load(this,R.raw.sound9 , 1);
        sound3 = sp.load(this,R.raw.sound9 , 1);
        sound4 = sp.load(this,R.raw.sound9 , 1);
        sound5 = sp.load(this, R.raw.sound9 , 1);
        sound6 = sp.load(this,R.raw.sound9 , 1);
        sound7 = sp.load(this,R.raw.sound9 , 1);
        sound8 = sp.load(this,R.raw.sound9, 1);
        sound9 = sp.load(this,R.raw.sound9 , 1);
        sound10 = sp.load(this,R.raw.sound9 , 1);
        sound11 = sp.load(this,R.raw.sound9 , 1);
        sound12 = sp.load(this,R.raw.sound9 , 1);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBeat();
            }
        });

        //thanks, https://www.youtube.com/watch?v=-eFoX4K59qM
        //thanks, https://medium.com/@ssaurel/create-an-audio-recorder-for-android-94dc7874f3d
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString() == null || name.getText().toString().equals("")) {
                    Toast.makeText(CustomMaker.this, "You need to specify a name first", Toast.LENGTH_SHORT).show();
                } else {
                    //Setup audio recorder to record g3p files using the mic
                    myAudioRecorder = new MediaRecorder();
                    myAudioRecorder.setAudioSamplingRate(8000);
                    myAudioRecorder.setAudioEncodingBitRate(12200);
                    myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

                    //Function call to create a folder if one doesn't exist already
                    makeDirectory();

                    //Make file path depending on the button last pressed
                    dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                            MainActivity.root + "/" +
                            name.getText().toString() + "/" +
                            "sound" + Integer.toString(lastPressed) +
                            ".3gp";

                    //Link file path to recorder
                    myAudioRecorder.setOutputFile(dir);

                    //boot the recorder
                    try {
                        myAudioRecorder.prepare();
                        myAudioRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //disable/enable buttons
                    record.setEnabled(false);
                    stop.setEnabled(true);
                    Toast.makeText(CustomMaker.this, "Recording...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //turn off recorder
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;

                //reenable buttons
                record.setEnabled(true);
                stop.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Stopped recording.", Toast.LENGTH_SHORT).show();

                //map the recorded sound to the pad
                if (lastPressed == 1) {
                    sound1 = sp.load(dir, 1);
                } else if (lastPressed == 2) {
                    sound2 = sp.load(dir, 1);
                } else if (lastPressed == 3) {
                    sound3 = sp.load(dir, 1);
                } else if (lastPressed == 4) {
                    sound4 = sp.load(dir, 1);
                } else if (lastPressed == 5) {
                    sound5 = sp.load(dir, 1);
                } else if (lastPressed == 6) {
                    sound6 = sp.load(dir, 1);
                } else if (lastPressed == 7) {
                    sound7 = sp.load(dir, 1);
                } else if (lastPressed == 8) {
                    sound8 = sp.load(dir, 1);
                } else if (lastPressed == 9) {
                    sound9 = sp.load(dir, 1);
                } else if (lastPressed == 10) {
                    sound10 = sp.load(dir, 1);
                } else if (lastPressed == 11) {
                    sound11 = sp.load(dir, 1);
                } else {
                    sound12 = sp.load(dir, 1);
                }
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
                Toast.makeText(getApplicationContext(), "Pad1 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 1;
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
                Toast.makeText(getApplicationContext(), "Pad2 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 2;
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
                Toast.makeText(getApplicationContext(), "Pad3 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 3;
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
                Toast.makeText(getApplicationContext(), "Pad4 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 4;
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
                Toast.makeText(getApplicationContext(), "Pad5 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 5;
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
                Toast.makeText(getApplicationContext(), "Pad6 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 6;
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
                Toast.makeText(getApplicationContext(), "Pad7 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 7;
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
                Toast.makeText(getApplicationContext(), "Pad8 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 8;
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
                Toast.makeText(getApplicationContext(), "Pad9 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 9;
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
                Toast.makeText(getApplicationContext(), "Pad10 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 10;
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
                Toast.makeText(getApplicationContext(), "Pad11 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 11;
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
                Toast.makeText(getApplicationContext(), "Pad12 Selected", Toast.LENGTH_SHORT).show();
                lastPressed = 12;
                return true;
            }
        });

    }

    public void saveBeat() {
        String folder = name.getText().toString();
        //thanks, https://stackoverflow.com/questions/24781213/how-to-create-a-folder-in-android-external-storage-directory
        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + MainActivity.root, folder);

        if (!f1.exists()) {
            f1.mkdirs();
        }
    }


    public void makeDirectory() {
        String folder = name.getText().toString();
        //thanks, https://stackoverflow.com/questions/24781213/how-to-create-a-folder-in-android-external-storage-directory
        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + MainActivity.root, folder);

        if (!f1.exists()) {
            f1.mkdirs();
        }
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
