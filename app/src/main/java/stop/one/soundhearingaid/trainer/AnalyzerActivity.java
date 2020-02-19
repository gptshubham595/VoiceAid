package stop.one.soundhearingaid.trainer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;
import stop.one.soundhearingaid.R;
import stop.one.soundhearingaid.home.HomeSections;
import stop.one.soundhearingaid.send.MyApplication;

public class AnalyzerActivity extends AppCompatActivity {
    TextView text;
    GifImageView starsappear, think;
    ImageView graph, rainbowtext;
    String imageString;
    Button retry, next;
    LinearLayout linear;
    String pitch;
    Double noOfStars = 0.0;
    private static final String BASE_URL = "http://ec2-13-235-23-241.ap-south-1.compute.amazonaws.com/uploadfile";
    File output;
    String outputFile;
    String respo = "NULL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyzer);
        graph = findViewById(R.id.graph);
        text = findViewById(R.id.text);
        think = findViewById(R.id.think);
        starsappear = findViewById(R.id.starsappear);
        retry = findViewById(R.id.retry);
        next = findViewById(R.id.next);
        linear = findViewById(R.id.linear);
        rainbowtext = findViewById(R.id.rainbowText);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.wav";



        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnalyzerActivity.this, TrainerActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnalyzerActivity.this, HomeSections.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        imageUpload();

    }

    public static Bitmap TrimBitmap(Bitmap bmp) {
        int imgHeight = bmp.getHeight();
        int imgWidth = bmp.getWidth();


        //TRIM WIDTH - LEFT
        int startWidth = 0;
        for (int x = 0; x < imgWidth; x++) {
            if (startWidth == 0) {
                for (int y = 0; y < imgHeight; y++) {
                    if (bmp.getPixel(x, y) != Color.WHITE) {
                        startWidth = x;
                        break;
                    }
                }
            } else break;
        }


        //TRIM WIDTH - RIGHT
        int endWidth = 0;
        for (int x = imgWidth - 1; x >= 0; x--) {
            if (endWidth == 0) {
                for (int y = 0; y < imgHeight; y++) {
                    if (bmp.getPixel(x, y) != Color.WHITE) {
                        endWidth = x;
                        break;
                    }
                }
            } else break;
        }


        //TRIM HEIGHT - TOP
        int startHeight = 0;
        for (int y = 0; y < imgHeight; y++) {
            if (startHeight == 0) {
                for (int x = 0; x < imgWidth; x++) {
                    if (bmp.getPixel(x, y) != Color.WHITE) {
                        startHeight = y;
                        break;
                    }
                }
            } else break;
        }


        //TRIM HEIGHT - BOTTOM
        int endHeight = 0;
        for (int y = imgHeight - 1; y >= 0; y--) {
            if (endHeight == 0) {
                for (int x = 0; x < imgWidth; x++) {
                    if (bmp.getPixel(x, y) != Color.WHITE) {
                        endHeight = y;
                        break;
                    }
                }
            } else break;
        }


        return Bitmap.createBitmap(
                bmp,
                startWidth,
                startHeight,
                endWidth - startWidth,
                endHeight - startHeight
        );

    }


    public Bitmap replaceColor(Bitmap src) {
        if (src == null)
            return null;
        int width = src.getWidth();
        int height = src.getHeight();
        int[] pixels = new int[width * height];
        src.getPixels(pixels, 0, 1 * width, 0, 0, width, height);
        for (int x = 0; x < pixels.length; ++x) {
            //    pixels[x] = ~(pixels[x] << 8 & 0xFF000000) & Color.BLACK;
            if (pixels[x] == Color.WHITE) pixels[x] = 0;
        }
        return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888);
    }

    private void imageUpload() {
        Response.Listener successListener = new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                Log.d("Response", response);
//                Toast.makeText(TrainerActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                String[] arrOfStr = response.split(",", 5);
                imageString = arrOfStr[0].substring(11, arrOfStr[0].length() - 3);
                pitch = arrOfStr[1].substring(9);
                Log.d("ARR", imageString);
                Log.d("ARR2", pitch);
                //decode base64 string to image
                byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                try {
                    decodedImage = TrimBitmap(decodedImage);
                    decodedImage = replaceColor(decodedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AnalyzerActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
                graph.setImageBitmap(decodedImage);

//                Log.d("ARR3",arrOfStr[2]);
//                String firstFourCharsbase64 = arrOfStr[1].substring(0, arrOfStr[1].length() - 9);
//                Toast.makeText(TrainerActivity.this, ""+arrOfStr[2], Toast.LENGTH_SHORT).show();
                noOfStars = Double.parseDouble(pitch);


                try {
                    Animation animationi = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_in);

                    if (noOfStars <= 54.0) {
                        starsappear.setImageResource(R.drawable.onestargif);
                        think.setImageResource(R.drawable.onestarhead);
                        text.setText("Oh no! Your pitch was off");
                    }

                    if (noOfStars <= 79.0 && noOfStars > 54.0) {
                        starsappear.setImageResource(R.drawable.twostargif);
                        think.setImageResource(R.drawable.twostarhead);
                        text.setText("Nice !! You can improve!!");
                    }

                    if (noOfStars > 79.0) {
                        starsappear.setImageResource(R.drawable.threestargif);
                        think.setImageResource(R.drawable.threestargifhead);
                        text.setText("That was perfect!!");
                    }


                    text.setAnimation(animationi);
                    starsappear.setAnimation(animationi);
                    think.setAnimation(animationi);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            think.setVisibility(View.INVISIBLE);

                            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.bottom_to_top);

                            starsappear.setAnimation(animation);
                            text.setAnimation(animation);

                            animation = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.fade_in);
                            graph.setVisibility(View.VISIBLE);
                            rainbowtext.setVisibility(View.VISIBLE);
                            linear.setVisibility(View.VISIBLE);

                            graph.setAnimation(animation);
                            rainbowtext.setAnimation(animation);
                            linear.setAnimation(animation);

                        }
                    }, 2000);
                    //DO NEXT

                } catch (Exception e) {
                    e.printStackTrace();
                }


//                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.GET, BASE_URL, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "multipart/mixed");
                Log.i("teste", "HEADER: " + headers);

                return headers;
            }
        };
        smr.setShouldCache(false);

        smr.addFile("file", outputFile);
        MyApplication.getInstance().addToRequestQueue(smr);

    }
}
