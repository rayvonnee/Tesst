package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class DocHomeFragment extends Fragment {

    ViewFlipper v_flipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.doc_fragment_home,null);

        int images[] = {R.drawable.cardiac1,R.drawable.cardiac2,R.drawable.cardiac3};

        v_flipper = v.findViewById(R.id.viewflipper2);
/*
        for(int i=0; i< images.length; i++){
            flipperImages(images[i]);
        }
*/

        for(int image: images){
            flipperImages(image);
        }
        return v;


    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
