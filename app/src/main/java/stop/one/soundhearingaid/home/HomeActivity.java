package stop.one.soundhearingaid.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import maes.tech.intentanim.CustomIntent;
import stop.one.soundhearingaid.R;

public class HomeActivity extends AppCompatActivity {
    LinearLayout btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeSections.class));
                CustomIntent.customType(HomeActivity.this, "fadein-to-fadeout");
            }
        });
    }
}
