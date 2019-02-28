package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VitalsFragment extends Fragment {

    private TextView Bloodsugar, BloodPressure, Weight;
    private EditText editTextBloodSugar, editTextBloodPressure, editTextWeight;
    private Button btnSaveVitals;

    private Button btnClearVitals;

    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vitals,null);

        firebaseAuth = FirebaseAuth.getInstance();


        Bloodsugar = (TextView) v.findViewById(R.id.textViewBloodSugar);
        BloodPressure = (TextView) v.findViewById(R.id.textViewBloodPressure);
        Weight = (TextView) v.findViewById(R.id.textViewWeight);

        editTextBloodSugar = (EditText) v.findViewById(R.id.editTextBloodSugar);
        editTextBloodPressure = (EditText) v.findViewById(R.id.editTextBloodPressure);
        editTextWeight = (EditText) v.findViewById(R.id.editTextWeight);

        btnSaveVitals = (Button) v.findViewById(R.id.btnSaveVitals);

        btnClearVitals = (Button) v.findViewById(R.id.btnClearVitals);


        btnSaveVitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BloodSugarkey = Bloodsugar.getText().toString();
                String BloodPressurekey = BloodPressure.getText().toString();
                String Weightkey = Weight.getText().toString();

                String BloodSugarvalue = editTextBloodSugar.getText().toString();
                String BloodPressurevalue = editTextBloodPressure.getText().toString();
                String Weightvalue = editTextWeight.getText().toString();

                String user_id = firebaseAuth.getCurrentUser().getUid();
                DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


                currrent_user_db.child(BloodSugarkey).setValue(BloodSugarvalue);
                currrent_user_db.child(BloodPressurekey).setValue(BloodPressurevalue);
                currrent_user_db.child(Weightkey).setValue(Weightvalue);

            }
        });


        btnClearVitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextBloodSugar.setText("");
                editTextBloodPressure.setText("");
                editTextWeight.setText("");

                String BloodSugarkey = Bloodsugar.getText().toString();
                String BloodPressurekey = BloodPressure.getText().toString();
                String Weightkey = Weight.getText().toString();

                String BloodSugarvalue = editTextBloodSugar.getText().toString();
                String BloodPressurevalue = editTextBloodPressure.getText().toString();
                String Weightvalue = editTextWeight.getText().toString();

                String user_id = firebaseAuth.getCurrentUser().getUid();
                DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);


                currrent_user_db.child(BloodSugarkey).setValue(BloodSugarvalue);
                currrent_user_db.child(BloodPressurekey).setValue(BloodPressurevalue);
                currrent_user_db.child(Weightkey).setValue(Weightvalue);
            }
        });
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        String user_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference dbgetsugar = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Blood Sugar");
        DatabaseReference dbgetpressure = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Blood Pressure");
        DatabaseReference dbgetweight = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Weight");



        dbgetsugar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String getSugarVal = String.valueOf(dataSnapshot.getValue());
                editTextBloodSugar.setText(getSugarVal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbgetpressure.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String getpressureVal = String.valueOf(dataSnapshot.getValue());
                editTextBloodPressure.setText(getpressureVal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbgetweight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String getweightVal = String.valueOf(dataSnapshot.getValue());
                editTextWeight.setText(getweightVal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




            }
}
