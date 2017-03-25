package com.example.menuka.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_semester);

        moduleList = ModuleData.getModules();
        moduleAdapter = new ModuleAdapter(this, 0, moduleList);

        ListView listView = (ListView) findViewById(R.id.modules_list_view);
        listView.setAdapter(moduleAdapter);
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
                startActivity(new Intent(SingleSemesterActivity.this, AddModuleActivity.class));
            }
        });
    }

}
