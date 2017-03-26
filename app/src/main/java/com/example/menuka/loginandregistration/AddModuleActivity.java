package com.example.menuka.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;

import firebase.Connection;
import models.Module;

public class AddModuleActivity extends AppCompatActivity {
    private Spinner gradesSpinner;
    private EditText codeEditText;
    private EditText nameEditText;
    private EditText creditsEditText;
    private String grade;
    private Button btnAdd;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);

        databaseReference = Connection.getINSTANCE().getDatabaseReference().child("semesters").child("1");

        gradesSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> gradesAdapter = ArrayAdapter.createFromResource(this, R.array.grades_array, android.R.layout.simple_spinner_dropdown_item);
        gradesSpinner.setAdapter(gradesAdapter);

        codeEditText = (EditText) findViewById(R.id.codeEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        creditsEditText = (EditText) findViewById(R.id.creditsEditText);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        grade = gradesSpinner.getSelectedItem().toString();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Module m = new Module();
                m.setCode(codeEditText.getText().toString().trim());
                m.setName(nameEditText.getText().toString().trim());
                m.setCredits(creditsEditText.getText().toString().trim());
                m.setGrade(grade);

                databaseReference.child(m.getCode()).setValue(m);
            }
        });
    }
}
