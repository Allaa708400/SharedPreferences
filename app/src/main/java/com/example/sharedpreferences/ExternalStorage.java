package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ExternalStorage extends AppCompatActivity {
    public static final String FILE_NAME ="users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                if (isExternalStorageWritable()){
                    try {
                    File ex_st = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File f = new File(ex_st,FILE_NAME);
                        f.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });

        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {

            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

            return true;
        }
        return false;
    }
}





