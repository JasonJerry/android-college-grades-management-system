package com.example.menuka.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.atomic.AtomicInteger;

import firebase.Connection;
import models.Semester;

public class AddSemesterActivity extends AppCompatActivity {
    private EditText numberEditText;
    private EditText yearEditText;
    private Button btnAdd;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);

        numberEditText = (EditText) findViewById(R.id.numberEditText);
        yearEditText = (EditText) findViewById(R.id.yearEditText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        databaseReference = Connection.getINSTANCE().getDatabaseReference().child("semesters");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Semester s = new Semester();
                s.setYear(yearEditText.getText().toString().trim());
                s.setNumber(numberEditText.getText().toString().trim());
                s.setEnabled(true);

                databaseReference.child(s.getNumber()).setValue(s);

                numberEditText.setText("");
                yearEditText.setText("");
            }
        });
        
        final AtomicInteger count = new AtomicInteger();

        ChildEventListener childEventListener = databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                // New child added, increment count
                int newCount = count.incrementAndGet();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
