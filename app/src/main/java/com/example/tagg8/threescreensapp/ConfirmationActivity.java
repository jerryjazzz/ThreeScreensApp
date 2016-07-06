package com.example.tagg8.threescreensapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.*;


public class ConfirmationActivity extends AppCompatActivity {
    private int phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        phoneNum = (int)intent.getSerializableExtra("phoneNum");
        String phoneText = String.valueOf(phoneNum);
        Log.v("Phone Text:", phoneText);

    }
}
