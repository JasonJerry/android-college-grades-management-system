package com.example.menuka.loginandregistration;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import firebase.Connection;
import models.Semester;

public class AddSemesterActivity extends AppCompatActivity {
    private EditText numberEditText;
    private EditText yearEditText;
    private Button btnAdd;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private int semesterCount;
    private boolean semesterAlreadyExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);

        initComponents();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Semester semester = new Semester();
                semester.setYear(yearEditText.getText().toString().trim());
                semester.setNumber(numberEditText.getText().toString().trim());
                semester.setEnabled(true);

                DatabaseReference dbRef = databaseReference.child("semesters").child(auth.getCurrentUser().getUid());
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        System.out.println("DataSnapshot: " + dataSnapshot.toString());
                        if(dataSnapshot.hasChild(semester.getNumber())){
                            // check if semester already exists before saving
                            semesterAlreadyExists = true;
                            System.out.println("Semester already exists: " + semesterAlreadyExists);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                System.out.println("semesterAlreadyExists: " + semesterAlreadyExists);
                if(semesterAlreadyExists){
                    System.out.println("About to show Alert Dialog");
                    AlertDialog alertDialog = new AlertDialog.Builder(AddSemesterActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Semester" + semester.getNumber() + " already exists");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else {
                    System.out.println("Semester does not exist");
                    dbRef.child(semester.getNumber()).setValue(semester);
                    numberEditText.setText("");
                    yearEditText.setText("");
//                    startActivity(new Intent(AddSemesterActivity.this, SemestersActivity.class));
                    AddSemesterActivity.this.finish();
                }
            }
        });

    }

    private void initComponents() {
        numberEditText = (EditText) findViewById(R.id.numberEditText);
        yearEditText = (EditText) findViewById(R.id.yearEditText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        databaseReference = Connection.getINSTANCE().getDatabaseReference();
        auth = FirebaseAuth.getInstance();
    }

    private void getSemesterCount(){
        databaseReference.child("semesters").child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                semesterCount = (int)dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
