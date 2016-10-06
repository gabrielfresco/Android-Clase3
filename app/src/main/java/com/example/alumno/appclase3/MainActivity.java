package com.example.alumno.appclase3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.github.florent37.materialtextfield.MaterialTextField;

public class MainActivity extends AppCompatActivity {
    public SharedPreferences prefs;
    private LoginController controller;
    private LoginModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);

        this.controller = new LoginController(this);
        this.model = new LoginModel(this);

        model.setController(controller);
        controller.setModel(model);

        model.setListeners();
    }

    @Override
    protected void onStart(){
        super.onStart();
        MaterialTextField mUsername = (MaterialTextField) findViewById(R.id.materialUsarnameLogin);
        MaterialTextField mPassword = (MaterialTextField) findViewById(R.id.materialPassword);
        mUsername.expand();
        mPassword.expand();

    }
}
