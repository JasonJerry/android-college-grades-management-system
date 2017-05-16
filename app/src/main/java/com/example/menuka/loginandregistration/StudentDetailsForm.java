package com.example.menuka.loginandregistration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import firebase.Connection;
import models.Student;


public class StudentDetailsForm extends AppCompatActivity {
    private Spinner departmentsSpinner;
    private Spinner genderSpinner;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText indexNoEditText;
    private EditText birthdayEditText;
    private Button btnSave;
    private DatabaseReference databaseReference;
    private ArrayAdapter<CharSequence> arrayAdapter;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

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

        birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        StudentDetailsForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = month + "/" + dayOfMonth + "/" + year;
                birthdayEditText.setText(date);
            }
        };

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // input validation
                String firstNameInput = firstNameEditText.getText().toString().trim();
                String lastNameInput = lastNameEditText.getText().toString().trim();
                String indexNoInput = indexNoEditText.getText().toString().trim();
                String birthdayInput = birthdayEditText.getText().toString().trim();

                if(firstNameInput.isEmpty()){
                    Toast.makeText(StudentDetailsForm.this, "First Name cannot be blank", Toast.LENGTH_SHORT).show();
                }else if(lastNameInput.isEmpty()){
                    Toast.makeText(StudentDetailsForm.this, "Last Name cannot be blank", Toast.LENGTH_SHORT).show();
                }else if(indexNoInput.isEmpty()){
                    Toast.makeText(StudentDetailsForm.this, "Index Number cannot be blank", Toast.LENGTH_SHORT).show();
                }else if(firstNameInput.length() > 100 ||
                        firstNameInput.length() < 1){
                    Toast.makeText(StudentDetailsForm.this, "First Name should contain 1 to 100 characters", Toast.LENGTH_SHORT).show();
                }else if(lastNameInput.length() > 100 ||
                        lastNameInput.length() < 1) {
                    Toast.makeText(StudentDetailsForm.this, "Last Name should contain 1 to 100 characters", Toast.LENGTH_SHORT).show();
                }else if(!Utils.isLegitIndexNumber(indexNoInput)) {
                    Toast.makeText(StudentDetailsForm.this, "Invalid Index Number", Toast.LENGTH_SHORT).show();
                }else if(birthdayInput.isEmpty()) {
                    Toast.makeText(StudentDetailsForm.this, "Birthday cannot be blank", Toast.LENGTH_SHORT).show();
                }else if(Utils.isLegitDateFormat(birthdayInput)) {
                    Toast.makeText(StudentDetailsForm.this, "Invalid Date Format", Toast.LENGTH_SHORT).show();
                }else if(Utils.isDateFuture(birthdayInput)){
                    Toast.makeText(StudentDetailsForm.this, "Birthday should be before today", Toast.LENGTH_SHORT).show();
                }else{
                    // everything's good
                    // end of input validation

                    Student student = new Student();
                    student.setFirstName(firstNameInput);
                    student.setLastName(lastNameInput);
                    student.setIndexNo(indexNoInput.toUpperCase());
                    student.setDepartment(departmentsSpinner.getSelectedItem().toString());
                    student.setGender(genderSpinner.getSelectedItem().toString());
                    student.setBirthday(birthdayInput);

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

            }
        });
    }

    private void initComponents() {
        databaseReference = Connection.getINSTANCE().getDatabaseReference().child("students");

        departmentsSpinner = (Spinner) findViewById(R.id.departmentsSpinner);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.deparments_array, android.R.layout.simple_spinner_dropdown_item);
        departmentsSpinner.setAdapter(arrayAdapter);

        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter genderArrayAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderArrayAdapter);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        indexNoEditText = (EditText) findViewById(R.id.indexNoEditText);
        birthdayEditText = (EditText) findViewById(R.id.birthdayEditText);
        btnSave = (Button) findViewById(R.id.btnSave);
    }

}
