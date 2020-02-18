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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.github.squti.androidwaverecorder.WaveRecorder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import stop.one.soundhearingaid.R;
import stop.one.soundhearingaid.Send.MyApplication;
import stop.one.soundhearingaid.Util;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TrainerActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://ec2-13-235-23-241.ap-south-1.compute.amazonaws.com/uploadfile";

    private static final int PICK_IMAGE_REQUEST = 100;
    GifImageView gif, gif2, speakergif;
    MediaPlayer mediaPlayer, mediaPlayer3;
    Button test;
    RelativeLayout rl;
    ImageView speaker, hear, wordsonly;
    Button go;
    TextView tap;
    int i = 0;
    WaveRecorder waveRecorder;
    String outputFile;
    ImageView again;
    Toolbar toolbar;

    SeekBar seekBar;
    boolean wasPlaying = false;
    private ProgressBar progressBar;

    private int pStatus = 0;
    Handler handler2 = new Handler();
    ImageView again2;
    GifImageView gifwordsonlyshow;
    File output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);
        tap = findViewById(R.id.tap);
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
        again2 = findViewById(R.id.again2);
        gifwordsonlyshow = findViewById(R.id.wordsonlyshow);
        gifwordsonlyshow.setVisibility(GONE);

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
                final int pro3 = (int) (seekBar.getProgress() * 0.01 * ((GifDrawable) gif.getDrawable()).getDuration());
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

                        gif.setImageResource(R.drawable.saying);
                        ((GifDrawable) gif2.getDrawable()).seekTo(pro3);
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
                output = new File(outputFile);
                if (output.exists()) {
                    Toast.makeText(TrainerActivity.this, "YES", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (outputFile != null) {
                                imageUpload();
                            } else {
                                Toast.makeText(getApplicationContext(), "Audio not selected!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 200);

//                    try {
//                       // sendaudio();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    Intent i = new Intent(TrainerActivity.this, AnalyzerActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(TrainerActivity.this, "Sorry unable to locate", Toast.LENGTH_SHORT).show();
                }


            }
        });

        again2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                again2.setVisibility(GONE);
                go.setVisibility(GONE);
                speaker.performClick();
                speaker.performClick();
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
                    gifwordsonlyshow.setVisibility(VISIBLE);
                    ((GifDrawable) gifwordsonlyshow.getDrawable()).stop();

                    speaker.setVisibility(VISIBLE);
                    tap.setVisibility(VISIBLE);
                    rl.setVisibility(GONE);
//                    gif.setVisibility(GONE);
                    gif2.setImageResource(0);
                    gif.setImageResource(R.drawable.saying);
                    ((GifDrawable) gif.getDrawable()).stop();

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
                tap.setVisibility(GONE);
                gifwordsonlyshow.setVisibility(GONE);
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

                            speakergif.setImageResource(0);
                            speakergif.setVisibility(VISIBLE);
                            speaker.setVisibility(GONE);

                            speakergif.setImageResource(R.drawable.speakervoice);
                            gif2.setImageResource(R.drawable.wordsonly);

                            go.setVisibility(GONE);
                            speaker.setVisibility(VISIBLE);
                            again2.setVisibility(GONE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    hear.setImageResource(R.drawable.me1);
                                    go.setVisibility(VISIBLE);
                                    speaker.setVisibility(GONE);
                                    again2.setVisibility(VISIBLE);
                                    ((GifDrawable) gif.getDrawable()).stop();

                                    speakergif.setVisibility(GONE);
                                    speaker.setVisibility(VISIBLE);
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
                            ((GifDrawable) gif.getDrawable()).stop();
                            go.setVisibility(VISIBLE);
                            speaker.setVisibility(GONE);
                            again2.setVisibility(VISIBLE);

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
                rl.setVisibility(GONE);
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

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rl.setVisibility(VISIBLE);
                    }
                }, 3000);


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


    private void startRecord() {
        waveRecorder = new WaveRecorder(outputFile);
        waveRecorder.setNoiseSuppressorActive(true);
        waveRecorder.startRecording();
        Toast.makeText(this, "Recording Voice", Toast.LENGTH_SHORT).show();
    }

    private void stopRecord() {
        waveRecorder.stopRecording();
        Toast.makeText(this, "Saved Recording", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void imageUpload() {
        Response.Listener successListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                Toast.makeText(TrainerActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jObj = new JSONObject(response);
                    String message = jObj.getString("message");

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, BASE_URL, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "multipart/mixed");
                Log.i("teste","HEADER: " + headers);

                return headers;
            }
        };
        
        smr.addFile("file", outputFile);
        MyApplication.getInstance().addToRequestQueue(smr);

    }


}
