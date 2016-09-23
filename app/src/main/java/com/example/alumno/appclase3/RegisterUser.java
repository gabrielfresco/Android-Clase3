package com.example.alumno.appclase3;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.register_activity_title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button register = (Button) findViewById(R.id.register_user_btn);
        final AppCompatEditText name = (AppCompatEditText)findViewById(R.id.name);
        final TextInputEditText surname = (TextInputEditText)findViewById(R.id.surname);
        final TextInputEditText username = (TextInputEditText)findViewById(R.id.username);
        final TextInputEditText password = (TextInputEditText)findViewById(R.id.password);
        final TextInputEditText confirm = (TextInputEditText)findViewById(R.id.confirm_password);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().length() < 5)
                    name.setError("Debe contener al menos 5 caracteres");
                if(surname.getText().toString().length() < 6)
                    surname.setError("Debe contener al menos 6 caracteres");
                if (!password.getText().toString().equals(confirm.getText().toString()))
                    password.setError("Las contraseñas no son iguales");
                  //  ValidationFragment fragment = ValidationFragment.newInstance("", "");
                   // getFragmentManager().beginTransaction().show(fragment);
                   // fragment.show(getFragmentManager(), "dialog");


                User user = new User();
            }
        });
    }
}
