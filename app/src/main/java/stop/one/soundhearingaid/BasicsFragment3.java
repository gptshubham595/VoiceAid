package stop.one.soundhearingaid;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

public class BasicsFragment3 extends Fragment implements Runnable{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basics3, container, false);
    }
    MediaPlayer mediaPlayer = new MediaPlayer();
    SeekBar seekBar;
    boolean wasPlaying = false;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playSong();
            }
        },550);
        final TextView seekBarHint = view.findViewById(R.id.textView);

        seekBar = view.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarHint.setVisibility(View.VISIBLE);
                int x = (int) Math.ceil(progress / 1000f);

                if (x < 10)
                    seekBarHint.setText("0:0" + x);
                else
                    seekBarHint.setText("0:" + x);

                double percent = progress / (double) seekBar.getMax();
                int offset = seekBar.getThumbOffset();
                int seekWidth = seekBar.getWidth();
                int val = (int) Math.round(percent * (seekWidth - 2 * offset));
                int labelWidth = seekBarHint.getWidth();
                seekBarHint.setX(offset + seekBar.getX() + val
                        - Math.round(percent * offset)
                        - Math.round(percent * labelWidth / 2));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarHint.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null ) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                    mediaPlayer.start();
                }

            }
        });
    }
    public void playSong() {

                try {


                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        seekBar.setProgress(0);
                        wasPlaying = true;

                    }


                    if (!wasPlaying) {

                        if (mediaPlayer == null) {
                            mediaPlayer = new MediaPlayer();
                        }



                        AssetFileDescriptor descriptor = getContext().getAssets().openFd("iwantotseerainbow.mp3");
                        mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();

                        mediaPlayer.prepare();
                        mediaPlayer.setVolume(0.5f, 0.5f);
                        mediaPlayer.setLooping(false);
                        seekBar.setMax(mediaPlayer.getDuration());

                        mediaPlayer.start();
                        new Thread(this).start();

                    }

                    wasPlaying = false;
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            public void run() {
                if(mediaPlayer.isPlaying())
                {int currentPosition = mediaPlayer.getCurrentPosition();
                int total = mediaPlayer.getDuration();


                while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
                    try {
                        Thread.sleep(1000);
                        currentPosition = mediaPlayer.getCurrentPosition();
                    } catch (InterruptedException e) {
                        return;
                    } catch (Exception e) {
                        return;
                    }

                    seekBar.setProgress(currentPosition);

                }
            }}

            private void clearMediaPlayer() {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

    }
