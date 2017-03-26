package com.example.menuka.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_form);

        databaseReference = Connection.getINSTANCE().getDatabaseReference().child("students");

        departmentsSpinner = (Spinner) findViewById(R.id.departmentsSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.deparments_array, android.R.layout.simple_spinner_dropdown_item);
        departmentsSpinner.setAdapter(arrayAdapter);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        indexNoEditText = (EditText) findViewById(R.id.indexNoEditText);
        birthdayEditText = (EditText) findViewById(R.id.birthdayEditText);
        btnSave = (Button) findViewById(R.id.btnSave);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

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
                }else{
                    Log.i("Error saving Student: ", "No user logged in.");
                }
            }
        });
    }
}
