package com.example.internalstoragebnhtrong;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bntluu,bntdoc;
    private TextView tvData;
    private final String fileName = "nguyenthelong.com";
    private final String content = "Blog nội dung của nguyễn thế long";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bntluu = findViewById(R.id.btn_luu);
        bntdoc = findViewById(R.id.btn_doc);
        tvData = findViewById(R.id.tv_Data);
        bntluu.setOnClickListener(this);
        bntdoc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_luu:
                //seveData();
                seveDatabyCahe();
                break;
            case R.id.btn_doc:
                //readData();
                readData2();
                break;
            default:
                break;
        }

    }

    public void seveData(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "lưu file thanh công", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seveDatabyCahe(){
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            file = new File(getCacheDir(),fileName);
            Log.d("MainAtivity", "seveDatabyCahe: ");
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData (){
        try {
            FileInputStream in = openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null ){
                buffer.append(line).append("\n");
            }
            tvData.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readData2 (){
        try {
            File file = new File(getCacheDir(),fileName);
            FileInputStream in = openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null ){
                buffer.append(line).append("\n");
            }
            tvData.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
