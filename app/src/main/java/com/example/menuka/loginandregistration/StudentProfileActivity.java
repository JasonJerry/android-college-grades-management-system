package com.example.menuka.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentProfileActivity extends AppCompatActivity {
    private Button btnAccountSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        btnAccountSettings = (Button) findViewById(R.id.btnAccountSettings);
        btnAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentProfileActivity.this, StudentDetailsForm.class));
            }
        });
    }
}
