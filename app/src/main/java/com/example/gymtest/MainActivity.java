package com.example.gymtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btnPlanActivity,btnAllActivities,btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        Utils.initTrainings();

        btnAllActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllTrainingsActivity.class);
                startActivity(intent);
            }
        });

        btnPlanActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlanActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About")
                        .setMessage("Created with <3 by Ambikesh at github.com/ambikesh-ak27\n" +
                                "Visit for more")
                        .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                                startActivity(intent);
                            }
                        });
                builder.create().show();
            }
        });
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");

        btnPlanActivity = findViewById(R.id.btnPlanActivity);
        btnAllActivities =  findViewById(R.id.btnAllActivities);
        btnAbout = findViewById(R.id.btnAbout);
    }
}