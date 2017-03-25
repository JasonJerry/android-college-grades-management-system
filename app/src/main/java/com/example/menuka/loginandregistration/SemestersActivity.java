package com.example.menuka.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import firebase.Connection;
import models.Semester;


public class SemestersActivity extends AppCompatActivity {

    private DatabaseReference databaseRef;
    private List<Semester> semesterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesters);

        semesterList = new ArrayList<>();

        databaseRef = Connection.getINSTANCE().getDatabaseReference();

        databaseRef.child("semesters").child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Semester s = dataSnapshot.getValue(Semester.class);
                System.out.println("Received s: " + s);
//                semesterList.add(s);
//                semesterList = dataSnapshot.getValue(Semester.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
