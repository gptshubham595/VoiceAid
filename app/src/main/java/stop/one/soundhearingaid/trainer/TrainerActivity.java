package stop.one.soundhearingaid.trainer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.github.squti.androidwaverecorder.WaveRecorder;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import stop.one.soundhearingaid.R;
import stop.one.soundhearingaid.Util;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TrainerActivity extends AppCompatActivity {

    GifImageView gif, gif2, speakergif;
    MediaPlayer mediaPlayer,mediaPlayer3;
    Button test;
    RelativeLayout rl;
    ImageView speaker, hear, wordsonly, go;
    int i = 0;
    WaveRecorder waveRecorder;
    String outputFile;
    ImageView again;
    Toolbar toolbar;

    SeekBar seekBar;
    boolean wasPlaying = false;

    private int pStatus = 0;
    Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

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
        seekBar = findViewById(R.id.seekbar);




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                final int pro = (int) ((seekBar.getProgress() - 20) * 0.001 * mediaPlayer.getDuration());
                final int pro2 = (int) (seekBar.getProgress() * 0.01 * ((GifDrawable) gif2.getDrawable()).getDuration());
//                Toast.makeText(TrainerActivity.this, pro2+"PRO" + pro+" GRE="+mediaPlayer.getDuration(), Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer = null;
                        mediaPlayer = MediaPlayer.create(TrainerActivity.this, R.raw.iwantotseerainbow);
                        mediaPlayer.seekTo(pro2);
                        gif2.setImageResource(0);
                        gif2.setImageResource(R.drawable.wordsonly);
                        ((GifDrawable) gif2.getDrawable()).seekTo(pro2);
                        ((GifDrawable) gif2.getDrawable()).start();
                        mediaPlayer.start();
                    }
                }, 50);


            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.iwantotseerainbow);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        }, 70);

        wordsonly.setVisibility(GONE);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.wav";

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TrainerActivity.this, AnalyzerActivity.class);
                startActivity(i);
//                CustomIntent.customType(TrainerActivity.this, "fadein-to-fadeout");

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rl.setVisibility(VISIBLE);
            }
        }, 3530);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        TrainerActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                        1
                );
                if (isPermissionGranted()) {
                    speaker.setVisibility(VISIBLE);

                    rl.setVisibility(GONE);
//                    gif.setVisibility(GONE);
                    gif2.setImageResource(0);
                    gif.setImageResource(R.drawable.saying);

                    hear.setVisibility(GONE);


                } else {
                    Toast.makeText(TrainerActivity.this, "Please Provide permission first", Toast.LENGTH_SHORT).show();
                    isPermissionGranted();
                    Util.requestPermission(TrainerActivity.this, Manifest.permission.RECORD_AUDIO);
                    Util.requestPermission(TrainerActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }


            }
        });


        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i % 2 != 0) {
                    mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.here);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

//                                notfinish


                            startRecord();
                            hear.setImageResource(0);
                            hear.setImageResource(R.drawable.hear);
                            hear.setVisibility(GONE);
                            gif.setVisibility(VISIBLE);
                            gif.setImageResource(R.drawable.saying);

                            if(((GifDrawable) gif.getDrawable()).isPlaying())
                            ((GifDrawable) gif.getDrawable()).stop();

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
                                    stopRecord();


                                }
                            }, 3500);


                        }
                    }, 50);


                } else {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stopRecord();
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
                }, 3000);
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

//                        Toast.makeText(TrainerActivity.this, "" + mediaPlayer.getDuration(), Toast.LENGTH_SHORT).show();

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startRecord() {
        waveRecorder = new WaveRecorder(outputFile);
        waveRecorder.setNoiseSuppressorActive(true);
        waveRecorder.startRecording();
    }

    private void stopRecord() {
        waveRecorder.stopRecording();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
