package stop.one.soundhearingaid.trainer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

import maes.tech.intentanim.CustomIntent;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import stop.one.soundhearingaid.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TrainerActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 101;
    GifImageView gif, gif2, speakergif;
    MediaPlayer mediaPlayer;
    Button test;
    RelativeLayout rl;
    ImageView speaker, hear, wordsonly, go;
    int i = 0;
    private MediaRecorder myAudioRecorder;
    String outputFile;
    ImageView again;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        Toast.makeText(this, "TAP ANYWHERE TO REPLAY", Toast.LENGTH_LONG).show();
        gif = findViewById(R.id.gif);
        gif2 = findViewById(R.id.gif2);
        test = findViewById(R.id.test);
        rl = findViewById(R.id.rl);
        speaker = findViewById(R.id.speaker);
        speakergif = findViewById(R.id.speakergif);
        hear = findViewById(R.id.hear);
        go = findViewById(R.id.go);
        wordsonly = findViewById(R.id.wordsonly);
        again = findViewById(R.id.again);
        mediaPlayer = MediaPlayer.create(this, R.raw.iwantotseerainbow);
        mediaPlayer.start();
        wordsonly.setVisibility(GONE);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TrainerActivity.this, AnalyzerActivity.class);
                startActivity(i);
                CustomIntent.customType(TrainerActivity.this, "fadein-to-fadeout");

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rl.setVisibility(VISIBLE);
            }
        }, 3030);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        TrainerActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                        1
                );
                if (isPermissionGranted()) {
                    rl.setVisibility(GONE);
                    gif.setVisibility(GONE);
                    gif2.setImageResource(0);
                    hear.setImageResource(R.drawable.me1);
                    hear.setVisibility(VISIBLE);
                    speaker.setVisibility(VISIBLE);

                } else {
                    Toast.makeText(TrainerActivity.this, "Please Provide permission first", Toast.LENGTH_SHORT).show();
                    isPermissionGranted();
                }


            }
        });


        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i % 2 != 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

//                                notfinish


                            startrecord();
                            hear.setImageResource(0);
                            hear.setImageResource(R.drawable.hear);
                            hear.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(TrainerActivity.this, "hi", Toast.LENGTH_SHORT).show();
                                }
                            });
                            speakergif.setImageResource(0);
                            speakergif.setVisibility(VISIBLE);

                            speakergif.setImageResource(R.drawable.speakervoice);
                            gif2.setImageResource(R.drawable.wordsonly);
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) speaker.getLayoutParams();
                            params.width = 77;
                            params.height = 77;
                            params.bottomMargin = 127;
                            speaker.setLayoutParams(params);
                            go.setVisibility(GONE);


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    hear.setImageResource(R.drawable.me1);
                                    ((GifDrawable) gif2.getDrawable()).stop();
                                    go.setVisibility(VISIBLE);
                                    speakergif.setVisibility(GONE);
                                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) speaker.getLayoutParams();
                                    params.width = 127;
                                    params.height = 127;
                                    params.bottomMargin = 97;
                                    speaker.setLayoutParams(params);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            stoprecord();
                                        }
                                    },200);

                                }
                            }, 3500);


                        }
                    }, 50);


                } else {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stoprecord();
                            hear.setImageResource(R.drawable.me1);
                            ((GifDrawable) gif2.getDrawable()).stop();
                            go.setVisibility(VISIBLE);
                            speakergif.setVisibility(GONE);
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) speaker.getLayoutParams();
                            params.width = 127;
                            params.height = 127;
                            params.bottomMargin = 97;
                            speaker.setLayoutParams(params);
                        }
                    }, 50);

                }
            }
        });


        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                again.setVisibility(GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        again.setVisibility(VISIBLE);
                    }
                },3000);
                gif.setImageResource(0);
                gif2.setImageResource(0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gif.setImageResource(R.drawable.saying);
                        gif2.setImageResource(R.drawable.wordsonly);
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.seekTo(0);
                        }
                        mediaPlayer.start();
                    }
                }, 50);


            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("", "Permission is granted");
        }
    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d("", "Permission is granted");
                return true;
            } else {
                Log.d("", "Permission is revoked");
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                        1
                );
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.d("", "Permission is granted");
            return true;
        }
    }


    private boolean TimerIs() {
        final boolean[] f = {false};
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                f[0] = true;
            }

        }.start();
        return false;
    }

    private void startrecord() {
        myAudioRecorder  = new MediaRecorder();
        myAudioRecorder .setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder .setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder .setOutputFile(outputFile);
        myAudioRecorder .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
    }

    private void stoprecord() {
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;

        Toast.makeText(getApplicationContext(), "Audio recorded successfully", Toast.LENGTH_LONG).show();

        //   doUpload(outputFile); //call the method to upload your file and perform upload.
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
