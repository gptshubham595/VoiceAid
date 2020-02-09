package stop.one.soundhearingaid.Home;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import stop.one.soundhearingaid.R;

public class HomeSections extends AppCompatActivity {
    ViewPager viewPager;
    FragmentPagerItemAdapter adapter;
    ImageView right, left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sections);
        viewPager = findViewById(R.id.viewPager);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);

        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("        ", HomeBasics.class)
                .add("        ", HomeBasics.class)
                .add("        ", HomeBasics.class)
                .add("        ", HomeBasics.class)
                .create());

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    left.setBackgroundTintList(ContextCompat.getColorStateList(HomeSections.this, R.color.light));
                    right.setBackgroundTintList(ContextCompat.getColorStateList(HomeSections.this, R.color.dark));
                } else if (position == viewPager.getAdapter().getCount() - 1) {
                    left.setBackgroundTintList(ContextCompat.getColorStateList(HomeSections.this, R.color.dark));
                    right.setBackgroundTintList(ContextCompat.getColorStateList(HomeSections.this, R.color.light));
                } else {
                    left.setBackgroundTintList(ContextCompat.getColorStateList(HomeSections.this, R.color.dark));
                    right.setBackgroundTintList(ContextCompat.getColorStateList(HomeSections.this, R.color.dark));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
