package com.example.menuka.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import firebase.Connection;
import models.Student;

public class StudentDetailsForm extends AppCompatActivity {
    private Spinner departmentsSpinner;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText indexNoEditText;
    private EditText birthdayEditText;
    private Button btnSave;
    private DatabaseReference databaseReference;
    private ArrayAdapter<CharSequence> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_form);

        initComponents();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Student s = dataSnapshot.getValue(Student.class);
                    if(s != null) {
                        firstNameEditText.setText(s.getFirstName());
                        lastNameEditText.setText(s.getLastName());
                        birthdayEditText.setText(s.getBirthday());
                        indexNoEditText.setText(s.getIndexNo());
                        departmentsSpinner.setSelection(arrayAdapter.getPosition(s.getDepartment()));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("DatabaseError on saving Student details: " + databaseError);

                }
            });
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setFirstName(firstNameEditText.getText().toString().trim());
                student.setLastName(lastNameEditText.getText().toString().trim());
                student.setIndexNo(indexNoEditText.getText().toString().trim());
                student.setBirthday(birthdayEditText.getText().toString().trim());
                student.setDepartment(departmentsSpinner.getSelectedItem().toString());

                if(firebaseAuth.getCurrentUser() != null){
                    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    student.setEmail(email);
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    student.setUserId(uid);

                    databaseReference.child(student.getUserId()).setValue(student);

                    startActivity(new Intent(StudentDetailsForm.this, StudentProfileActivity.class));
                    StudentDetailsForm.this.finish();
                }else{
                    Log.i("Error saving Student: ", "No user logged in.");
                }
            }
        });
    }

    private void initComponents() {
        databaseReference = Connection.getINSTANCE().getDatabaseReference().child("students");

        departmentsSpinner = (Spinner) findViewById(R.id.departmentsSpinner);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.deparments_array, android.R.layout.simple_spinner_dropdown_item);
        departmentsSpinner.setAdapter(arrayAdapter);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        indexNoEditText = (EditText) findViewById(R.id.indexNoEditText);
        birthdayEditText = (EditText) findViewById(R.id.birthdayEditText);
        btnSave = (Button) findViewById(R.id.btnSave);
    }
}
