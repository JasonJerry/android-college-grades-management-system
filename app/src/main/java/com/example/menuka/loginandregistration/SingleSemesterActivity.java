package com.example.menuka.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView recyclerView;
    private ModuleAdapter moduleAdapter;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_semester);

        moduleList = ModuleData.getModules();
        moduleAdapter = new ModuleAdapter(this, 0, moduleList);

        ListView listView = (ListView) findViewById(R.id.modules_list_view);
        listView.setAdapter(moduleAdapter);

        databaseRef = Connection.getINSTANCE().getDatabaseReference();

        databaseRef.child("semesters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
