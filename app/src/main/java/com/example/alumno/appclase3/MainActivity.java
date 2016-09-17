package com.example.alumno.appclase3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        if(prefs.getBoolean("isLogged", false))
            this.startActivity(new Intent(this,Recycler_view.class));


        Button btnLogin = (Button) findViewById(R.id.loginBtn);

        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CheckBox remember_me = (CheckBox) findViewById(R.id.rememberme);
                    if (remember_me != null && remember_me.isChecked()) {
                        prefs.edit().putBoolean("isLogged", true).apply();
                    }
                    Intent intent = new Intent(getApplicationContext(),Recycler_view.class);
                    startActivity(intent);
                }
            });
        }
    }


}
