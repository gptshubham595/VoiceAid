package stop.one.soundhearingaid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

public class BasicsFragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basics4, container, false);

    }

    MediaPlayer mediaPlayer, mediaPlayer2;
    ImageView play, play2;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());


        play = view.findViewById(R.id.play1);
        play2 = view.findViewById(R.id.play2);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.here);
        mediaPlayer2 = MediaPlayer.create(getContext(), R.raw.stressing);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.here);
                mediaPlayer2 = MediaPlayer.create(getContext(), R.raw.stressing);


                mediaPlayer.seekTo(0);
                mediaPlayer2.seekTo(0);
                mediaPlayer2.release();
                mediaPlayer.start();
                play.setImageResource(R.drawable.play);
                play2.setImageResource(R.drawable.play2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        play.setImageResource(R.drawable.play2);
                    }
                }, 3000);
            }
        });
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.here);
                mediaPlayer2 = MediaPlayer.create(getContext(), R.raw.stressing);

                mediaPlayer.seekTo(0);
                mediaPlayer2.seekTo(0);
                mediaPlayer.release();
                mediaPlayer2.start();
                play2.setImageResource(R.drawable.play);
                play.setImageResource(R.drawable.play2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        play2.setImageResource(R.drawable.play2);

                    }
                }, 3000);
            }
        });


    }


}