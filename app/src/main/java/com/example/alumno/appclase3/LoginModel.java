package com.example.alumno.appclase3;

import android.widget.EditText;

/**
 * Created by alumno on 06/10/2016.
 */
public class LoginModel {
    private LoginController controller;
    private MainActivity act;
    public User user;

    public LoginModel(MainActivity act){
        this.act = act;
        user = new User();
        user.setUsername(((EditText)act.findViewById(R.id.username)).getText().toString());
    }
    public void setController(LoginController controller){
        this.controller = controller;
    }
    public void setListeners(){
        controller.onClickListener(this.act.findViewById(R.id.login_btn),R.id.login_btn);
        controller.onClickListener(this.act.findViewById(R.id.register_btn), R.id.register_btn);
    }
}
