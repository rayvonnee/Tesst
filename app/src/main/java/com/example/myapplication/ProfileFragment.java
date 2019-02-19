package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private TextView textViewName;
    private TextView textViewAge;
    private FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,null);

        textViewName = (TextView) v.findViewById(R.id.textViewProfileName);
        textViewAge = (TextView) v.findViewById(R.id.textViewProfileAge);
        firebaseAuth = firebaseAuth.getInstance();


        String u_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference currrent_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Name");
        DatabaseReference currrent_user_db2 = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id).child("Age");


        currrent_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childName = String.valueOf(dataSnapshot.getValue());
                textViewName.setText(childName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        currrent_user_db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String childAge = String.valueOf(dataSnapshot.getValue());
                textViewAge.setText(childAge);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"You are inside Profile Fragment", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
