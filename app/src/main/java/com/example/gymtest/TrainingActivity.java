package com.example.gymtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface {
    private static final String TAG = "TrainingActivity";

    public static final String TRAINING_KEY = "training";

    @Override
    public void getPlan(Plan plan) {
        Log.d(TAG, "getPlan: Plan: "+ plan.toString());

        if (Utils.addPlan(plan)){
            Toast.makeText(this, plan.getTraining().getName() + " added successfully to your plan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    private TextView txtName, txtDescription;
    private Button btnAddToPlan;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            Training training = intent.getParcelableExtra(TRAINING_KEY);
            if (null != training) {
                txtName.setText(training.getName());
                txtDescription.setText(training.getLongDesc());
                Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(image);

                btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlanDetailDialog dialog = new PlanDetailDialog();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(TRAINING_KEY, training);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(),"Plan Detail Dialog");
                    }
                });
            }
        }
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");
        txtName = findViewById(R.id.txtName);
        txtDescription =  findViewById(R.id.txtDescription);
        btnAddToPlan = findViewById(R.id.btnAddToPlan);
        image =  findViewById(R.id.trainingImage);
    }
}