package stop.one.soundhearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import maes.tech.intentanim.CustomIntent;
import pl.droidsonroids.gif.GifImageView;

public class Welcome extends AppCompatActivity {
    TextView hi, put,txt;
    ImageView go,img;
    GifImageView cat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        go = findViewById(R.id.go);
        hi = findViewById(R.id.hi);
        img = findViewById(R.id.img);
        txt = findViewById(R.id.text);
        put = findViewById(R.id.put);
        cat = findViewById(R.id.cat);

        String name = "Arav";
        hi.setText("Hey " + name + ", welcome to Bolo!");
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                go.setVisibility(View.GONE);
                hi.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);

                Animation animationi = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade_in);
                cat.setVisibility(View.VISIBLE);
                put.setVisibility(View.VISIBLE);

                cat.startAnimation(animationi);
                put.startAnimation(animationi);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Welcome.this,MainActivity.class));
                        CustomIntent.customType(Welcome.this,"fadein-to-fadeout");
                    }
                },800);
            }
        });
    }
}
