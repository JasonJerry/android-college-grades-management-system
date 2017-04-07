package com.example.menuka.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapters.FeedbackAdapter;
import firebase.Connection;
import models.Feedback;

public class ReceivedFeedbackActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private ListView feedbackListView;
    private FeedbackAdapter adapter;
    private FirebaseAuth auth;
    private ArrayList<Feedback> feedbackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_feedback);

        initComponents();
        auth = FirebaseAuth.getInstance();

        databaseReference.child("feedback").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedbackList = new ArrayList<Feedback>();
                adapter = new FeedbackAdapter(ReceivedFeedbackActivity.this, R.layout.single_feedback_card, feedbackList);
                feedbackListView.setAdapter(adapter);

                for(DataSnapshot child: dataSnapshot.getChildren()){
                    databaseReference.child("feedback").child(auth.getCurrentUser().getUid()).child(child.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Feedback feedback = dataSnapshot.getValue(Feedback.class);
                            feedbackList.add(feedback);
                            adapter.notifyDataSetChanged();
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

    }

    private void initComponents() {
        databaseReference = Connection.getINSTANCE().getDatabaseReference();
        feedbackListView = (ListView) findViewById(R.id.feedback_list_view);
    }
}
