package stop.one.soundhearingaid.trainer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;
import stop.one.soundhearingaid.R;
import stop.one.soundhearingaid.home.HomeActivity;

public class AnalyzerActivity extends AppCompatActivity {
    TextView text;
    GifImageView starsappear, think;
    ImageView graph, rainbowtext;
    String imageString;
    Button retry, next;
    LinearLayout linear;

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

        imageString = "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";
        //decode base64 string to image
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        try {
            decodedImage = TrimBitmap(decodedImage);
            decodedImage = replaceColor(decodedImage);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
        graph.setImageBitmap(decodedImage);

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
                Intent i = new Intent(AnalyzerActivity.this, HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animationi = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade_in);

                text.setText("That was perfect!!");
                starsappear.setImageResource(R.drawable.threestargif);
                think.setImageResource(R.drawable.threestargifhead);

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
            }
        }, 4000);
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
}
