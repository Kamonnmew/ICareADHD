package com.example.kamon.icareadhd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ChooseLanguage extends AppCompatActivity {

    private int anInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        String[] strings = new String[]{"Eng", "Thai"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(ChooseLanguage.this,
                android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                anInt = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                anInt = 0;
            }
        });


    }   // Main Method


    public void clickUpdateLanguage(View view) {

        Log.d("12febV1", "i ==> " + anInt);
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DatabaseUser.DB_NAME,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(DatabaseUser.TABLE_Language, null, null);

        //Update Value to LanguageTABLE
        MyManage myManage = new MyManage(ChooseLanguage.this);
        myManage.addValueLanguage(Integer.toString(anInt));
        startActivity(new Intent(ChooseLanguage.this, MainActivity.class));
        finish();

    }   // update

}   // Main Class
