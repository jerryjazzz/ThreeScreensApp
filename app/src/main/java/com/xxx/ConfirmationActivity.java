package com.xxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.*;


public class ConfirmationActivity extends AppCompatActivity {
    private long phoneNum;
    private EditText editConfirm;
    private EditText editName;
    private int confirmNum;
    private String confirmName;
    private String phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        phoneNum = (long)intent.getSerializableExtra("phoneNum");
        editConfirm = (EditText) findViewById(R.id.enter_code);
        editName = (EditText) findViewById(R.id.enter_name);
        confirmName = editName.getText().toString();
        phoneText = String.valueOf(phoneNum);
        Log.v("Phone Text:", phoneText);

    }

    public void confirmClick(View view) {
        String yourUrl = "";
        confirmNum = Integer.parseInt(editConfirm.getText().toString());
        if(confirmNum % 17 == 0) {
            yourUrl += confirmName + phoneText;
        }
        else {
            Toast.makeText(ConfirmationActivity.this, "Not a valid confirmation number!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
