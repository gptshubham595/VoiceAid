package stop.one.soundhearingaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class SplashActivity extends AppCompatActivity {
ImageView bolo,comingsoon,back;
TextView text;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    //
        bolo=findViewById(R.id.image);
        back=findViewById(R.id.back);
        comingsoon=findViewById(R.id.coming);
        text=findViewById(R.id.text);
        btn=findViewById(R.id.shubham);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SplashActivity.this, PlanBuild.class);
                startActivity(i);
//                CustomIntent.customType(SplashActivity.this,"fadein-to-fadeout");
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.bottom_to_top);

                bolo.startAnimation(animation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animation animationi = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.fade_in);
                        text.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.VISIBLE);
                        comingsoon.setVisibility(View.VISIBLE);
                        back.setVisibility(View.VISIBLE);

                        text.startAnimation(animationi);
                        btn.startAnimation(animationi);
                        comingsoon.startAnimation(animationi);
                        back.startAnimation(animationi);    }
                }, 1200);

            }
        }, 3000);




        //

    }
}
