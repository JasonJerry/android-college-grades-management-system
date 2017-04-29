package com.example.menuka.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;

import firebase.Connection;
import models.Module;

public class EditModuleActivity extends AppCompatActivity {
    private Spinner gradesSpinner;
    private EditText codeEditText;
    private EditText nameEditText;
    private EditText creditsEditText;
    private Button btnSave;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private String semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_module);

        databaseReference = Connection.getINSTANCE().getDatabaseReference();
        auth = FirebaseAuth.getInstance();

        String name = getIntent().getStringExtra("name");
        final String code = getIntent().getStringExtra("code");
        String credits = getIntent().getStringExtra("credits");
        final String[] grade = {getIntent().getStringExtra("grade")};
        final String[] newGrade = {""};
        semester = getIntent().getStringExtra("semester");

        codeEditText = (EditText) findViewById(R.id.codeEditText);
        codeEditText.setText(code);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        nameEditText.setText(name);

        creditsEditText = (EditText) findViewById(R.id.creditsEditText);
        creditsEditText.setText(credits);

        gradesSpinner = (Spinner) findViewById(R.id.editGradesSpinner);
        ArrayAdapter<CharSequence> gradesAdapter = ArrayAdapter.createFromResource(this, R.array.grades_array, android.R.layout.simple_spinner_dropdown_item);
        gradesSpinner.setAdapter(gradesAdapter);

        final String[] grades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "I-we", "I-ce", "F", "Absent"};

        gradesSpinner.setSelection(Arrays.asList(grades).indexOf(grade[0]));
        gradesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newGrade[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSave = (Button) findViewById(R.id.editButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Module m = new Module();
                m.setName(nameEditText.getText().toString().trim());
                m.setCode(codeEditText.getText().toString().trim());
                m.setCredits(creditsEditText.getText().toString().trim());
                m.setGrade(newGrade[0]);

                // delete the existing module
                databaseReference.child("semesters")
                        .child(auth.getCurrentUser().getUid())
                        .child(semester)
                        .child("modules")
                        .child(code).removeValue();

                // add new module
                databaseReference.child("semesters")
                        .child(auth.getCurrentUser().getUid())
                        .child(semester)
                        .child("modules")
                        .child(m.getCode())
                        .setValue(m);

                // go back to SingleSemesterActivity
                onBackPressed();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, SingleSemesterActivity.class);
        i.putExtra("semester", semester);
        startActivity(i);
        this.finish();
    }
}
