package stop.one.soundhearingaid;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

public class BasicsFragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basics3, container, false);
    }

    public static MediaPlayer mediaPlayer;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer = MediaPlayer.create(getContext(), R.raw.loudness);
                    mediaPlayer.seekTo(0);
//                    mediaPlayer.start();
//                    mediaPlayer.setLooping(true);

                    volumeSeekbar = view.findViewById(R.id.seekbar);
                    audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
                    volumeSeekbar.setMax(audioManager
                            .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                    volumeSeekbar.setProgress(audioManager
                            .getStreamVolume(AudioManager.STREAM_MUSIC));


                    volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onStopTrackingTouch(SeekBar arg0) {
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar arg0) {
                        }

                        @Override
                        public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                    progress, 0);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 550);


    }


}
