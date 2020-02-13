package stop.one.soundhearingaid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    FragmentPagerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("        ", BasicsFragment1.class)
                .add("        ", BasicsFragment2.class)
                .add("        ", BasicsFragment3.class)
                .add("        ", BasicsFragment4.class)
                .create());

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean lastPageChange = false;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int lastIdx = viewPager.getAdapter().getCount() - 1;
                if (lastPageChange && position == lastIdx) {
                    startActivity(new Intent(MainActivity.this, Avatar.class));
                    CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int lastIdx = viewPager.getAdapter().getCount() - 1;
                if (lastIdx == 3 /*&& lastPos==lastIdx*/ && state == 1) lastPageChange = true;
                else lastPageChange = false;
            }
        });
        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }

}


















