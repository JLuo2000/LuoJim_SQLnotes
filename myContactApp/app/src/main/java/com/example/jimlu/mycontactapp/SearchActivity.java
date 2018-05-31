package com.example.jimlu.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // get the intent that initiated this activity
        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //capture the layout's textView and set the string as the text
        android.widget.TextView textView = findViewById(R.id.textView4);
        textView.setText(message);
        android.widget.TextView numView = findViewById(R.id.textView5);
        numView.setText(message);
        android.widget.TextView addView = findViewById(R.id.textView6);
        addView.setText(message);
    }
}
