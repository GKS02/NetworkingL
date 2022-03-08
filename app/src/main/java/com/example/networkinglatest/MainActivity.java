 package com.example.networkinglatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatextView();
            }
        });
    }

     private void  updatextView() {
         //TextView textView=findViewById(R.id.textView);
        //Make network call here
         NetworkTask networkTask = new NetworkTask();

         //networkTask.execute("https://www.google.com","https://www.github.com");
         networkTask.execute("https://api.github.com/users");
     }

     class NetworkTask extends AsyncTask<String,Void,String> {

         @Override
         protected String doInBackground(String... strings) {
             String stringUrl = strings[0];
             try {
                 URL url = new URL(stringUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scn = new Scanner(inputStream);
                scn.useDelimiter("\\A");

                if(scn.hasNext()) {
                    String s = scn.next();
                    return s;
                }
             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             return "Failed to load";
         }

         @Override
         protected void onPostExecute(String s) {
             TextView textView=findViewById(R.id.textView);
             textView.setText(s);
             super.onPostExecute(s);
         }
     }
 }