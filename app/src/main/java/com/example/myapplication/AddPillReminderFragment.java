package com.example.myapplication;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;



public class AddPillReminderFragment extends Fragment {
    private TextView txtsettime;
    private Button btneditime;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_addpillreminder,null);

       txtsettime = v.findViewById(R.id.txtsettime);

        final Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
       btneditime = v.findViewById(R.id.btnsettime);
       btneditime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       txtsettime.setText(hourOfDay + ":" + minute);
                   }
               }, hour, minute, true);
               timePickerDialog.show();
           }
       });

       return v;
    }


}

