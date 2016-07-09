package com.mywallet.sirva.mywallet.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mywallet.sirva.mywallet.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);




        if(false) {
            Intent myIntent = new Intent(LaunchActivity.this, MainActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
            LaunchActivity.this.startActivity(myIntent);
        }else {
            Intent myIntent = new Intent(LaunchActivity.this, LoginActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
            LaunchActivity.this.startActivity(myIntent);
        }
    }
}
