package stop.one.soundhearingaid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import maes.tech.intentanim.CustomIntent;
import pl.droidsonroids.gif.GifImageView;

public class PlanBuild extends AppCompatActivity {
    EditText code;
    TextView idonthavecode, just, text;
    ImageView go, back;
    GifImageView machine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        code = findViewById(R.id.code);
        idonthavecode = findViewById(R.id.idonthave);
        go = findViewById(R.id.go);
        machine = findViewById(R.id.machine);
        back = findViewById(R.id.back);
        just = findViewById(R.id.just);
        text = findViewById(R.id.text);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codegot = code.getText().toString().trim();
                //fetchplan
                //If Success
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        code.setVisibility(View.GONE);
                        go.setVisibility(View.GONE);
                        text.setVisibility(View.GONE);
                        idonthavecode.setVisibility(View.GONE);

                        machine.setVisibility(View.VISIBLE);
                        back.setVisibility(View.VISIBLE);
                        just.setVisibility(View.VISIBLE);

                        Animation animationi = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.fade_in);
                        machine.startAnimation(animationi);
                        back.startAnimation(animationi);
                        just.startAnimation(animationi);

                    }
                }, 500);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(PlanBuild.this, Welcome.class));
                        CustomIntent.customType(PlanBuild.this, "fadein-to-fadeout");
                    }
                }, 5000);

            }
        });
    }
}
