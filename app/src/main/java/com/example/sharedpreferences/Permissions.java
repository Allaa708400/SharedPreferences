package com.example.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Permissions extends AppCompatActivity {
    public static final String FILE_NAME ="users";
    public static final int WRITE_EX_REQ_CODE =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_save = findViewById(R.id.btn_save);
        Button btn_res = findViewById(R.id.btn_res);
        EditText et_username = findViewById(R.id.et_user_name);
        EditText et_brithDate = findViewById(R.id.et_birth_date);
        EditText et_email = findViewById(R.id.et_email);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            String permissions = (Manifest.permission.WRITE_EXTERNAL_STORAGE);
            ActivityCompat.requestPermissions(this, new String[]{permissions},WRITE_EX_REQ_CODE);

        }
        else {

        }


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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        switch (requestCode){
            case WRITE_EX_REQ_CODE:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){



                    Toast.makeText(this, "تم الحصول على الصلاحيه", Toast.LENGTH_SHORT).show();
                }
                return;
        }
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