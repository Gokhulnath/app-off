package com.android.tiktokclone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import com.android.tiktokclone.R;

public class Frag2 extends Fragment {

    ImageView iv_image;
    public Frag2()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag1,container,false);
        iv_image=view.findViewById(R.id.frag1IV);
        iv_image.setImageResource(R.drawable.ic_actor);

        TextView tv_text=view.findViewById(R.id.Frag1TV);
        tv_text.setText("For actors");


        return view;
    }

    public void setMenuVisibility(boolean menuVisible) {
        if(menuVisible){
            YoYo.with(Techniques.Pulse)
                    .duration(900)
                    .repeat(2)
                    .playOn(iv_image);
        }
        super.setMenuVisibility(menuVisible);
    }
}
