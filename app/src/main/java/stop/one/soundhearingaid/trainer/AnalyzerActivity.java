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

        imageString = "iVBORw0KGgoAAAANSUhEUgAAAoAAAAHgCAYAAAA10dzkAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAPYQAAD2EBqD+naQAAADh0RVh0U29mdHdhcmUAbWF0cGxvdGxpYiB2ZXJzaW9uMy4xLjEsIGh0dHA6Ly9tYXRwbG90bGliLm9yZy8QZhcZAAAgAElEQVR4nO3dd2DcZ2H/8c8NnbZkbUuyhvdIHO/YznC240xGCIGGEBJKoJS2/IACv1BKWlpGKVCgFNqE/AIEaBKyE2fHTux42/GIty1rWJYsa4+7083fH04kfe90mid5PO/XX9xz6yvbwW9/v9/neWzhcDgsAAAAGMN+pg8AAAAA44sABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgAAGAYAhAAAMAwBCAAAIBhCEAAAADDEIAAAACGIQABAAAMQwACAAAYhgAEAAAwDAEIAABgGAIQAADAMAQgAACAYQhAAAAAwxCAAAAAhiEAAQAADEMAAgAAGIYABAAAMAwBCAAAYBgCEAAAwDAEIAAAgGEIQAAAAMMQgAAAAIYhAAEAAAxDAAIAABiGAAQAADAMAQgA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";

        try{imageString=getIntent().getStringExtra("base64");}catch (Exception e){e.printStackTrace();
            Toast.makeText(this, "Using Old", Toast.LENGTH_SHORT).show();}


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
