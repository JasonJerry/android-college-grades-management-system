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

public class SemestersActivity extends AppCompatActivity {
    private TextView ogpaLabel;
    private Button btnAddSemester;
    private DatabaseReference databaseReference;
    private List<Semester> semesterList;
    private SemesterAdapter semesterAdapter;
    private ListView semesterListView;
    private static FirebaseAuth auth;

    // TODO: lookUp onPause(), onDestroy() methods

    public void startSingleSemesterActivity(String number){
        Intent i = new Intent(this, SingleSemesterActivity.class);
        i.putExtra("semester", number);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesters);

        initComponents();

        ogpaLabel.setText("OGPA: N/A");

        databaseReference = Connection.getINSTANCE().getDatabaseReference();

        // value listener for semesters
        databaseReference.child("semesters").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // values are read from the database
                semesterList = new ArrayList<>();
                semesterAdapter = new SemesterAdapter(SemestersActivity.this, R.layout.single_semester_on_profile, semesterList, SemestersActivity.this);
                semesterListView.setAdapter(semesterAdapter);
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    // for each semester related to the authenticated Student
                    databaseReference.child("semesters").child(auth.getCurrentUser().getUid()).child(child.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            System.out.println("DataSnapshotError: " + dataSnapshot.toString());
                            Semester semester = dataSnapshot.getValue(Semester.class);
                            semesterList.add(semester);
                            semesterAdapter.notifyDataSetChanged();
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
                finish();
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
