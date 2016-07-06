package com.example.tagg8.threescreensapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import java.util.*;

import java.lang.Object.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private EditText editMobile;
    private int phoneNum;
    private String deviceName;
    private List<String> emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMobile = (EditText) findViewById(R.id.editText1);
        editMobile.setImeActionLabel("Custom text", KeyEvent.KEYCODE_ENTER);
        getLoaderManager().initLoader(0, null, this);
        deviceName = android.os.Build.MODEL;
        Log.v("Device name:", deviceName);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle arguments) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(
                        ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE + " = ?",
                new String[]{ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            // Potentially filter on ProfileQuery.IS_PRIMARY
            cursor.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    public void nextClick(View view) {
        String yourURL = "";
        phoneNum = Integer.valueOf(editMobile.getText().toString());
        yourURL += phoneNum;
        int phoneLength = (int)(Math.log10(phoneNum)+1);
        if(phoneLength >= 11 && phoneLength <= 15) {
            for(String email : emails) {
                Log.v("Email Address:", email);
            }
            Intent i = new Intent(getApplicationContext(), ConfirmationActivity.class);
            i.putExtra("phoneNum", phoneNum);
            startActivity(i);
        }
        else {
            Toast.makeText(MainActivity.this, "Not a valid international phone number!",
                    Toast.LENGTH_LONG).show();
        }
        /*try {
            URL yourLink = new URL(yourURL);
            HttpURLConnection urlConnection = (HttpURLConnection) yourLink.openConnection();

        }catch (Exception e) {

        }*/
    }

}
