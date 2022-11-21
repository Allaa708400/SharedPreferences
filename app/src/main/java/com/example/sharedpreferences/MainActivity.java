package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class MainActivity extends AppCompatActivity {

public static final String FILE_NAME ="users";
    SharedPreferences.Editor edit;
    EditText et_username,et_brithDate,et_email;
public final String USERNAME_KEY = "username";
    public final String EMAIL_KEY = "email";
    public final String BIRTHDATE_KEY = "birthDate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        setContentView(R.layout.activity_main);
        Button btn_save = findViewById(R.id.btn_save);
        Button btn_res = findViewById(R.id.btn_res);
        EditText et_username = findViewById(R.id.et_user_name);
        EditText et_brithDate = findViewById(R.id.et_birth_date);
        EditText et_email = findViewById(R.id.et_email);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String email = et_email.getText().toString();
                String birthDate = et_brithDate.getText().toString();
                edit.putString(USERNAME_KEY, username);
                edit.putString(EMAIL_KEY, email);
                edit.putString(BIRTHDATE_KEY,birthDate );
                edit.apply();
            }
        });
        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = sp.getString(USERNAME_KEY, "Null");
                String email = sp.getString(EMAIL_KEY, "Null");
                String birthDate = sp.getString(BIRTHDATE_KEY, "Null");
                Toast.makeText(MainActivity.this, username+" | "+email+" |"+birthDate,
                Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
   }
    public void saveData(){
        String username = et_username.getText().toString();
        String email = et_email.getText().toString();
        String birthDate = et_brithDate.getText().toString();
        edit.putString(USERNAME_KEY, username);
        edit.putString(EMAIL_KEY, email);
        edit.putString(BIRTHDATE_KEY,birthDate );
        edit.apply();
    }
}