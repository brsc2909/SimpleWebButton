package com.example.brsc2909.SimpleWebButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.IOException;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> postDataParams = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addListenerOnButton();

    }


    private void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final String user = sharedPref.getString("username", "null");
        final String pass = sharedPref.getString("password", "null");
        final String server = sharedPref.getString("server", "");
        final String port = sharedPref.getString("serverPort", "");


        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(user);
                System.out.println(pass);

                String page = "open";

                PostData post = new PostData();
                Post datapost = new Post();
                try {
                    postDataParams.put("username", String.valueOf(user));
                    postDataParams.put("password", String.valueOf(pass));
                    datapost.postData(server, port, page, postDataParams);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.preferences: {
                Intent intent = new Intent();
                intent.setClassName(this, "com.example.brsc2909.SimpleWebButton.MyPreferenceActivity");
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}

