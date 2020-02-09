package stop.one.soundhearingaid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Avatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }
    public void screenTapped(View view) {
        Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show();
    }
}
