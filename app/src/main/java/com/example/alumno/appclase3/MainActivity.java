package com.example.alumno.appclase3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.florent37.materialtextfield.MaterialTextField;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MaterialTextField mUsername = (MaterialTextField) findViewById(R.id.materialUsarnameLogin);
        MaterialTextField mPassword = (MaterialTextField) findViewById(R.id.materialPassword);
        mUsername.expand();
        mPassword.expand();

        this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);

        if(prefs.getBoolean("isLogged", false))
            this.startActivity(new Intent(this,Recycler_view.class));

        final EditText txtEmail = (EditText) findViewById(R.id.username);
        Button btnLogin = (Button) findViewById(R.id.login_btn);
        Button btnRegister = (Button) findViewById(R.id.register_btn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterUser.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        if (btnLogin != null) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prefs.edit().putString("username", txtEmail.getText().toString()).apply();
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
