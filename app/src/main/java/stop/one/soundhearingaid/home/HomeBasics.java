package stop.one.soundhearingaid.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import maes.tech.intentanim.CustomIntent;
import stop.one.soundhearingaid.R;
import stop.one.soundhearingaid.trainer.TrainerActivity;

public class HomeBasics extends Fragment {
    TextView item_subtitle;
    RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homebasics1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments()) + 1;
        item_subtitle = view.findViewById(R.id.item_subtitle);
        item_subtitle.setText("Sessions " + position);
        relativeLayout1=view.findViewById(R.id.stage1);

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getContext(), TrainerActivity.class);
                i.putExtra("stage","1");
                startActivity(i);
                CustomIntent.customType(getContext(),"fadein-to-fadeout");
            }
        });

        relativeLayout2=view.findViewById(R.id.stage2);

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getContext(), TrainerActivity.class);
                i.putExtra("stage","2");
                startActivity(i);
                CustomIntent.customType(getContext(),"fadein-to-fadeout");
            }
        });

        relativeLayout3=view.findViewById(R.id.stage3);

        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getContext(), TrainerActivity.class);
                i.putExtra("stage","3");
                startActivity(i);
                CustomIntent.customType(getContext(),"fadein-to-fadeout");
            }
        });


    }

}