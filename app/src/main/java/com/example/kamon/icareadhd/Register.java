package com.example.kamon.icareadhd;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class Register extends Activity {

    //Explicit
    private DatabaseUser mHelper;
    private SQLiteDatabase mDb;
    private Spinner spinner;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //Bind Widget
        spinner = (Spinner) findViewById(R.id.spinner);

        //For Spinner
        setupSpinner();


        mHelper = new DatabaseUser(this);
        mDb = mHelper.getWritableDatabase();
        final EditText editUserType = (EditText) findViewById(R.id.edit_usertype);
        final EditText editFName = (EditText) findViewById(R.id.edit_fname);
        final EditText editLName = (EditText) findViewById(R.id.edit_lname);
        final EditText editEMail = (EditText) findViewById(R.id.edit_email);
        final EditText editPass = (EditText) findViewById(R.id.edit_pass);

        Button buttonDone = (Button) findViewById(R.id.Register_buttondone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = editUserType.getText().toString();
                String fname = editFName.getText().toString();
                String lname = editLName.getText().toString();
                String email = editEMail.getText().toString();
                String pass = editPass.getText().toString();
                if (type.length() != 0 && fname.length() != 0 && lname.length() != 0 && email.length() != 0 && pass.length() != 0) {
                    Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DatabaseUser.TABLE_NAME
                            + " WHERE " + DatabaseUser.COL_TYPE + "='" + type + "'"
                            + " AND " + DatabaseUser.COL_FNAME + "='" + fname + "'"
                            + " AND " + DatabaseUser.COL_LNAME + "='" + lname + "'"
                            + " AND " + DatabaseUser.COL_EMAIL + "='" + email + "'"
                            + " AND " + DatabaseUser.COL_PASS + "='" + pass + "'", null);
                    if (mCursor.getCount() == 0) {
                        mDb.execSQL("INSERT INTO " + DatabaseUser.TABLE_NAME + " ("
                                + DatabaseUser.COL_TYPE + ", "
                                + DatabaseUser.COL_FNAME + ", "
                                + DatabaseUser.COL_LNAME + ", "
                                + DatabaseUser.COL_EMAIL + ", "
                                + DatabaseUser.COL_PASS + ") VALUES ('" + type
                                + "', '" + fname + "', '" + lname + "', '" + email + "', '" + pass + "');");
                        editUserType.setText("");
                        editFName.setText("");
                        editLName.setText("");
                        editEMail.setText("");
                        editPass.setText("");
                        Toast.makeText(getApplicationContext(), "Create User Successful"
                                , Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "This Users is already have"
                                , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }   // Main Class

    private void setupSpinner() {

        String[] strings = new String[]{"พ่อแม่", "คุณครู", "หมอ"};

    }   // setupSpinner


    public void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        mHelper.close();
        mDb.close();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }
}