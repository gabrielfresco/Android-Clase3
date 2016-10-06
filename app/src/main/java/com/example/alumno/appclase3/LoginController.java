package com.example.alumno.appclase3;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by alumno on 06/10/2016.
 */
public class LoginController {
    private MainActivity act;
    private LoginModel model;

    public LoginController(MainActivity act){
        this.act = act;
    }

    public void setModel(LoginModel model){
        this.model = model;
    }

    public void onClickListener(View v, Integer id){

        switch (id){
            case R.id.login_btn:
                ((Button)v).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText txtEmail = (EditText) act.findViewById(R.id.username);
                        model.user.setUsername(txtEmail.getText().toString());
                        if( model.user.getUsername().toLowerCase().equals("gabi")){
                            act.prefs.edit().putString("username", model.user.getUsername()).apply();
                            CheckBox remember_me = (CheckBox) act.findViewById(R.id.rememberme);
                            if (remember_me != null && remember_me.isChecked()) {
                                act.prefs.edit().putBoolean("isLogged", true).apply();
                            }
                            Intent intent = new Intent(act.getApplicationContext(),CategoriesList.class);
                            act.startActivity(intent);
                        }else{
                            txtEmail.setError("Usuario o contraseña incorrectos");
                        }
                    }
                });
                break;
            case R.id.register_btn:
                ((Button)v).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(act.getApplicationContext(),RegisterUser.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        act.startActivity(intent);
                    }
                });
                break;
        }

    }

    public void checkUserState(){
        if(act.prefs.getBoolean("isLogged", false))
            act.startActivity(new Intent(act, CategoriesList.class));
    }
}
