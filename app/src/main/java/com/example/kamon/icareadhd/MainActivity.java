package com.example.kamon.icareadhd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //explicit
    DatabaseUser mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;

    private int intLanguage = 0;
    private MyConstant myConstant;
    private String[] emailStrings, passStrings, loginStrings, createStrings;
    private DatabaseUser databaseUser;
    private EditText Uname, Password;
    private Button button, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup
        databaseUser = new DatabaseUser(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = databaseUser.getWritableDatabase();


        //set up
        myConstant = new MyConstant();
        emailStrings = myConstant.getEmailButtonMain();
        passStrings = myConstant.getPasswordButtonMain();
        loginStrings = myConstant.getLoginButtonMain();
        createStrings = myConstant.getCreateAccountButtonMain();


        mHelper = new DatabaseUser(this);
        mDb = mHelper.getReadableDatabase();
        button = (Button) findViewById(R.id.login_buttoncreacc);
        button2 = (Button) findViewById(R.id.login_buttonlogin);
        Uname = (EditText) findViewById(R.id.login_uname);
        Password = (EditText) findViewById(R.id.login_password);

        //Check Language
        checkLanguage();



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = Uname.getText().toString();
                String pass = Password.getText().toString();
                /**Cursor mCursor = mDb.rawQuery("SELECT * FROM my_table");
                 mCursor = db.rawQuery("SELECT email FROM" +DatabaseUser.TABLE_NAME+ "WHERE"+ DatabaseUser.COL_EMAIL + "='" +uname+ "AND" + DatabaseUser.COL_PASS + "='" + pass, new String[] {uname, pass});
                 if(mCursor.moveToFirst()) {
                 Toast.makeText(getApplicationContext(), "Log in Success", Toast.LENGTH_SHORT).show();*/
                Intent i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
                /**} else {
                 Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                 }*/
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
    }   // Main Method



    private void showView() {
        Uname.setText(emailStrings[intLanguage]);
        Password.setText(passStrings[intLanguage]);
        button.setText(createStrings[intLanguage]);
        button2.setText(loginStrings[intLanguage]);
    }


    private void checkLanguage() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DatabaseUser.DB_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM languageTABLE", null);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            startActivity(new Intent(MainActivity.this, ChooseLanguage.class));
            finish();
        } else {

            intLanguage = Integer.parseInt(cursor.getString(1));
            showView();

        }

    }   // checkLanguage
}
