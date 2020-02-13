package stop.one.soundhearingaid.motivation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import stop.one.soundhearingaid.R;

public class MotivationActivity extends AppCompatActivity {
    ViewPager viewPager;
    FragmentPagerItemAdapter adapter;
    TextView skip;
    ImageView cancel;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);

        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("        ", MotivationFragment1.class)
                .add("        ", MotivationFragment1.class)
                .add("        ", MotivationFragment2.class)
                .create());
        skip = findViewById(R.id.skip);
        cancel = findViewById(R.id.cancel);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean lastPageChange = false;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int lastIdx = viewPager.getAdapter().getCount() - 1;
                if (lastPageChange && position == lastIdx) {
                    finish();
                }
            }

            @Override
            public void onPageSelected(int position) {
            pos=position;
                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getItemPosition(pos+1));
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int lastIdx = viewPager.getAdapter().getCount() - 1;
                if (lastIdx == 3 /*&& lastPos==lastIdx*/ && state == 1) lastPageChange = true;
                else lastPageChange = false;
            }
        });
        final SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
