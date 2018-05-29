package com.example.jimlu.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    EditText editNum;
    EditText editAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editNum = findViewById(R.id.editText_Number);
        editAdd = findViewById(R.id.editText_address);

        myDb = new DatabaseHelper (this);
        Log.d("MyContactApp", "MainActivity : instantiated Databasehelper");
    }

    public void addData (View view){
        Log.d("MyContactApp", "MainActivity : Add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(),editNum.getText().toString(),editAdd.getText().toString());
        if (isInserted){
            Toast.makeText(MainActivity.this,"Success, Contact inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Failed, Contact not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData (View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp", "MainActivity: viewData: received cursor");
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("ID: " + res.getString(0));
            buffer.append("\n");
            buffer.append("name: " + res.getString(1));
            buffer.append("\n");
            buffer.append("number: " + res.getString(2));
            buffer.append("\n");
            buffer.append("address: " + res.getString(3));
            buffer.append("\n");
        }
        Log.d("MyContactApp", "MainActivity: viewData: assembled stringbuffer");
        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title, String message) {
        Log.d("MyContactApp", "MainActivity: showMessage: building alert dialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

    }
    public static final String EXTRA_MESSAGE = "com.example.jimlu.mycontactapp.MESSAGE";
    public void SearchDB(View view){
        Log.d("MyContactApp", "MainActivity: launching SearchDB");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, editName.getText().toString());
        startActivity(intent);

    }
}
