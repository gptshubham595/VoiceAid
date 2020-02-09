package stop.one.soundhearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import maes.tech.intentanim.CustomIntent;
import stop.one.soundhearingaid.Home.HomeActivity;

public class Avatar extends AppCompatActivity {
    RelativeLayout txtlay, avatarlay;
    ImageView boy, girl, monkey, koala;
    ImageView go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        txtlay = findViewById(R.id.txtlay);
        avatarlay = findViewById(R.id.avatarlayout);
        go = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Avatar.this, HomeActivity.class));
                CustomIntent.customType(Avatar.this, "fadein-to-fadeout");
            }
        });
        boy = findViewById(R.id.boy);
        girl = findViewById(R.id.girl);
        monkey = findViewById(R.id.monkey);
        koala = findViewById(R.id.koala);
        girl.setAlpha(85);
        monkey.setAlpha(85);
        koala.setAlpha(85);
        boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girl.setAlpha(85);
                monkey.setAlpha(85);
                koala.setAlpha(85);
                boy.setAlpha(255);
            }
        });
        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girl.setAlpha(255);
                boy.setAlpha(85);
                monkey.setAlpha(85);
                koala.setAlpha(85);
            }
        });
        monkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girl.setAlpha(85);
                boy.setAlpha(85);
                koala.setAlpha(85);
                monkey.setAlpha(255);
            }
        });
        koala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girl.setAlpha(85);
                monkey.setAlpha(85);
                boy.setAlpha(85);
                koala.setAlpha(255);
            }
        });
    }

    public void screenTapped(View view) {
        txtlay.setVisibility(View.GONE);
        avatarlay.setVisibility(View.VISIBLE);

    }
}
