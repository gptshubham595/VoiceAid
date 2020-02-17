package stop.one.soundhearingaid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import stop.one.soundhearingaid.Badge.Badge1;
import stop.one.soundhearingaid.Badge.Badge2;
import stop.one.soundhearingaid.Badge.Badge3;
import stop.one.soundhearingaid.Badge.Badge4;
import stop.one.soundhearingaid.Badge.Badge5;
import stop.one.soundhearingaid.R;
import stop.one.soundhearingaid.home.HomeBasics;
import stop.one.soundhearingaid.home.HomeBasics2;
import stop.one.soundhearingaid.home.HomeSections;

public class MyBadgesFragment extends Fragment {
    ViewPager viewPager;
    FragmentPagerItemAdapter adapter;
    ImageView right, left;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_badges, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
        viewPager = view.findViewById(R.id.viewPager);
        right = view.findViewById(R.id.right);
        left = view.findViewById(R.id.left);
        adapter = new FragmentPagerItemAdapter(
                getFragmentManager(), FragmentPagerItems.with(getContext())
                .add("        ", Badge1.class)
                .add("        ", Badge2.class)
                .add("        ", Badge3.class)
                .add("        ", Badge4.class)
                .add("        ", Badge5.class)

                .create());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    left.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.light));
                    right.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.dark));
                } else if (position == viewPager.getAdapter().getCount() - 1) {
                    left.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.dark));
                    right.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.light));
                } else {
                    left.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.dark));
                    right.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.dark));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
