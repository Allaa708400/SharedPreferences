package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class InternedStorage extends AppCompatActivity {
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
                try {
                  //  File f = new File(getCacheDir(),FILE_NAME);
                    File f = new File(getFilesDir(),FILE_NAME);
                    if (!f.exists()){
                        f.createNewFile();
                    }
                   // f.delete();
                    FileOutputStream fos =new FileOutputStream(f,true);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(username+","+email+","+birthDate);
                    pw.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    String allText = "";
                    String temp = "";
                    while ((temp= br.readLine())!=null){
                        allText +=temp;
                    }
                    br.close();
                    isr.close();
                    fis.close();
                   Toast.makeText(InternedStorage.this, allText, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}