package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentsFragment extends Fragment {

    TextView textviewaptview, getTextviewaptnew;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vu =  inflater.inflate(R.layout.fragment_appointments,null);
        textviewaptview = (TextView) vu.findViewById(R.id.textViewaptview);
        getTextviewaptnew = (TextView) vu.findViewById(R.id.textViewaptnew);
        viewPager = (ViewPager) vu.findViewById(R.id.fragment_container);
        pagerViewAdapter = new PagerViewAdapter(getChildFragmentManager());

        viewPager.setAdapter(pagerViewAdapter);

        textviewaptview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        getTextviewaptnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                onChangeTab(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }


        });


    return vu;


    }
    public void onChangeTab(int i) {
        if(i == 0){
            textviewaptview.setTextSize(15);

            getTextviewaptnew.setTextSize(20);
        }
        if(i == 1){
            textviewaptview.setTextSize(20);

            getTextviewaptnew.setTextSize(15);
        }
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
