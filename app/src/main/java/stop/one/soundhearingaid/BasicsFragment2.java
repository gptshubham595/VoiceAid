package stop.one.soundhearingaid;

import android.content.res.Resources;
import android.media.AudioManager;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.nightonke.wowoviewpager.Animation.WoWoPathAnimation;
import com.nightonke.wowoviewpager.WoWoPathView;
import com.nightonke.wowoviewpager.WoWoViewPager;
import com.nightonke.wowoviewpager.WoWoViewPagerAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import static android.view.View.GONE;
import static stop.one.soundhearingaid.MainActivity.audioPlayer;

public class BasicsFragment2 extends Fragment {
    WoWoPathView pathView;
    public int r;
    public boolean animationAdded = false;
    WoWoViewPager wowo;
    int screenW = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenH = Resources.getSystem().getDisplayMetrics().heightPixels;

    public int fragmentNumber() {
        return 4;
    }


    public Integer[] fragmentColorsRes() {
        return new Integer[]{
                R.color.white,
                R.color.white,
                R.color.white,
                R.color.white,
        };
    }

    public static boolean yes2 = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basics2, container, false);
    }

    int i=0;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
        pathView = view.findViewById(R.id.path_view);
        wowo = view.findViewById(R.id.wowo_viewpager);
        final ImageView bird = view.findViewById(R.id.bird);
        final ImageView tryme = view.findViewById(R.id.tryme);
        final ImageView arrow = view.findViewById(R.id.arrow);

        SeekBar seekBar = view.findViewById(R.id.seekbar);

        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i==0) {
                    tryme.setVisibility(GONE);
                    arrow.setVisibility(GONE);
                    Animation animationi = AnimationUtils.loadAnimation(getContext(),
                            R.anim.slideoutleft);
                    bird.setAnimation(animationi);
                    bird.setVisibility(GONE);
                }
                i++;
            }
        });

        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {

                yes2 = true;
                audioPlayer.start();

                audioPlayer.setLooping(true);

                arrow.setVisibility(View.GONE);
                tryme.setVisibility(View.GONE);

            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                PlaybackParams params = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    yes2 = true;
                    params = new PlaybackParams();
                    float pro=progress*0.01f;
                    params.setPitch(pro);
                    audioPlayer.setPlaybackParams(params);
                }
            }
        });

        wowo.setAdapter(WoWoViewPagerAdapter.builder()
                .fragmentManager(getFragmentManager())
                .count(fragmentNumber())                       // Fragment Count
                .colorsRes(fragmentColorsRes())                // Colors of fragments
                .build());
        setPageTV(wowo);

        addAnimations();

    }


    public void addAnimations() {
        if (animationAdded) return;
        animationAdded = true;

        addRocketAnimation();


        wowo.ready();

    }

    public void addRocketAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) pathView.setZ(50);

        Log.d("W", screenW + "");
        Log.d("H", screenH + "");

        pathView.newPath()
                .pathMoveTo(-screenW * 0.139, screenH * 0.38)
                .pathCubicTo(screenW * 0.185, screenH * 0.26,
                        screenW * 0.37, screenH * 0.76,
                        screenW * 0.55, screenH * 0.317)
                .pathCubicTo(screenW * 0.648, screenH * 0.206,
                        screenW * 0.7407, screenH * -.04,
                        screenW * 0.97, screenH * 0.206)
        ;
        wowo.addAnimation(pathView)
                .add(WoWoPathAnimation.builder().page(0).from(0).to(0.50).path(pathView).build())
                .add(WoWoPathAnimation.builder().page(1).from(0.50).to(0.75).path(pathView).build())
                .add(WoWoPathAnimation.builder().page(2).from(0.75).to(1).path(pathView).build())
                .add(WoWoPathAnimation.builder().page(3).from(0.75).to(1).path(pathView).build());
    }

    public int color(int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }

    public void setPageTV(WoWoViewPager wowo) {
        wowo.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
    }
}