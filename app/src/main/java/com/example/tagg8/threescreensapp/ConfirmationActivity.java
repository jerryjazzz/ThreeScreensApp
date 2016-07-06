package com.example.tagg8.threescreensapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;


public class ConfirmationActivity extends AppCompatActivity {
    private int phoneNum;
    private EditText editConfirm;
    private EditText editName;
    private int confirmNum;
    private String confirmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        phoneNum = (int)intent.getSerializableExtra("phoneNum");
        editConfirm = (EditText) findViewById(R.id.enter_code);
        editName = (EditText) findViewById(R.id.enter_name);
        confirmName = editName.getText().toString();
        String phoneText = String.valueOf(phoneNum);
        Log.v("Phone Text:", phoneText);

    }

    public void confirmClick(View view) {
        String yourUrl = "";
        confirmNum = Integer.valueOf(editConfirm.getText().toString());
        int remainder = confirmNum % 17;
        if(remainder == 0) {
            yourUrl += confirmName + phoneNum;
        }
        else {
            Toast.makeText(ConfirmationActivity.this, "Not a valid confirmation number!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
