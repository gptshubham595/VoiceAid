package stop.one.soundhearingaid.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import stop.one.soundhearingaid.MainActivity;
import stop.one.soundhearingaid.R;

public class HomeSections extends AppCompatActivity {
    ViewPager viewPager;
    FragmentPagerItemAdapter adapter;
    ImageView right, left;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sections);
        viewPager = findViewById(R.id.viewPager);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("        ", HomeBasics.class)
                .add("        ", HomeBasics2.class)
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
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
