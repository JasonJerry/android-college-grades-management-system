package com.example.menuka.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapters.SemesterAdapter;
import firebase.Connection;
import models.Semester;
import models.Student;


public class SemestersActivity extends AppCompatActivity {
    private TextView ogpaLabel;
    private Button btnAddSemester;
    private DatabaseReference databaseReference;
    private List<Semester> semesterList;
    private SemesterAdapter semesterAdapter;
    private ListView semesterListView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesters);

        initComponents();

        ogpaLabel.setText("OGPA: N/A");

        semesterList = new ArrayList<>();
        semesterAdapter = new SemesterAdapter(this, R.layout.single_semester_on_profile, semesterList);
        semesterListView.setAdapter(semesterAdapter);

        databaseReference = Connection.getINSTANCE().getDatabaseReference();

        // one time listener for reading all semesters
        databaseReference.child("semesters").child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1; i<=dataSnapshot.getChildrenCount(); i++){
                    // for each semester related to the authenticated Student
                    databaseReference.child("semesters").child(auth.getCurrentUser().getUid()).child(Integer.toString(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Semester semester = dataSnapshot.getValue(Semester.class);
                            semesterList.add(semester);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAddSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SemestersActivity.this, AddSemesterActivity.class));
            }
        });
    }

    private void initComponents() {
        ogpaLabel = (TextView) findViewById(R.id.ogpaLabel);
        btnAddSemester = (Button) findViewById(R.id.btnAddSemester);
        auth = FirebaseAuth.getInstance();
        semesterListView = (ListView) findViewById(R.id.semesters_list_view);
    }
}
