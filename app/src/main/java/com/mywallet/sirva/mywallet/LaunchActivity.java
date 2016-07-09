package com.mywallet.sirva.mywallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);




        if(true) {
            Intent myIntent = new Intent(LaunchActivity.this, MainActivity.class);
            myIntent.putExtra("key", 5); //Optional parameters
            LaunchActivity.this.startActivity(myIntent);
        }else {
            Intent myIntent = new Intent(LaunchActivity.this, LoginActivity.class);
            myIntent.putExtra("key", 5); //Optional parameters
            LaunchActivity.this.startActivity(myIntent);
        }
    }
}
