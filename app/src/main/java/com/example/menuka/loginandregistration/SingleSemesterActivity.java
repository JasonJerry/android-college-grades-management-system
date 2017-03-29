package com.example.menuka.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapters.ModuleAdapter;
import dummy_data.ModuleData;
import firebase.Connection;
import models.Module;

public class SingleSemesterActivity extends AppCompatActivity {
    private List<Module> moduleList;
    private ModuleAdapter moduleAdapter;
    private DatabaseReference databaseRef;
    private Button btnAddModule;
    private FirebaseAuth auth;
    private ListView modulesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_semester);

        moduleList = new ArrayList<>();
        modulesListView = (ListView) findViewById(R.id.modules_list_view);

        final String semester = getIntent().getStringExtra("semester");

        auth = FirebaseAuth.getInstance();

        databaseRef = Connection.getINSTANCE().getDatabaseReference()
                .child("semesters")
                .child(auth.getCurrentUser().getUid())
                .child(semester)
                .child("modules");

        final ListView listView = (ListView) findViewById(R.id.modules_list_view);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // dataSnapshot contains every module in the semester
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Module m = child.getValue(Module.class);
                    moduleList.add(m);
                }

                moduleAdapter = new ModuleAdapter(SingleSemesterActivity.this, R.layout.module_card, moduleList, semester);
                modulesListView.setAdapter(moduleAdapter);
                listView.setAdapter(moduleAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAddModule = (Button) findViewById(R.id.btnAddModule);

        databaseRef = Connection.getINSTANCE().getDatabaseReference().child("semesters");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAddModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SingleSemesterActivity.this, AddModuleActivity.class);
                i.putExtra("semester", semester);
                startActivity(i);
                finish();
            }
        });
    }

}
