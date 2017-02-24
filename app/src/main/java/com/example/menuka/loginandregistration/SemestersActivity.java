package com.example.menuka.loginandregistration;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ramotion.foldingcell.FoldingCell;

public class SemestersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesters);

        // get out folding cell
        final FoldingCell foldingCell = (FoldingCell) findViewById(R.id.folding_cell);

        // set custom parameters
//        foldingCell.initialize(1000, Color.DKGRAY, 2);

        // or with camera height parameter
//        foldingCell.initialize(cameraHeight, animationDuration, backSideColor, additionalFlipCounts);
//        foldingCell.initialize(1000, Color.DKGRAY, 0);

        foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
            }
        });
    }
}
