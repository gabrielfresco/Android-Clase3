package com.example.alumno.appclase3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.florent37.materialtextfield.MaterialTextField;

import java.io.IOException;
import java.util.TreeMap;

public class RegisterUser extends AppCompatActivity implements Handler.Callback, Connection_problem.OnFragmentInteractionListener {
    private HttpManager manager;
    private Thread hilo;
    private Handler handler;
    private TreeMap<String, String> params;
    private FragmentManager fm;
    private RequestThread requestThread;
    public SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        manager = new HttpManager();
        this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.register_activity_title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button register = (Button) findViewById(R.id.register_user_btn);
        final AppCompatEditText name = (AppCompatEditText)findViewById(R.id.name);
        final TextInputEditText surname = (TextInputEditText)findViewById(R.id.surname);
        final TextInputEditText username = (TextInputEditText)findViewById(R.id.username);
        final TextInputEditText email = (TextInputEditText)findViewById(R.id.email);
        final TextInputEditText password = (TextInputEditText)findViewById(R.id.password);
        final TextInputEditText confirm = (TextInputEditText)findViewById(R.id.confirm_password);

        handler = new Handler(this);
        params = new TreeMap<String, String>();
        fm = getSupportFragmentManager();

        assert register != null;
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean hasErrors = false;

                if(name.getText().toString().length() < 5){
                    name.setError("Debe contener al menos 5 caracteres");
                    hasErrors = true;
                }

                if(surname.getText().toString().length() < 6) {
                    surname.setError("Debe contener al menos 6 caracteres");
                    hasErrors = true;
                }
                if (!password.getText().toString().equals(confirm.getText().toString())) {
                    password.setError("Las contraseñas no son iguales");
                    hasErrors = true;
                }


                if(ConnectionUtils.isConnected(getApplicationContext())){
                    params.put("nombre", name.getText().toString());
                    params.put("apellido",surname.getText().toString());
                    params.put("usuario",username.getText().toString());
                    params.put("email", email.getText().toString());
                    params.put("password", password.getText().toString());
                    prefs.edit().putString("password", password.getText().toString()).apply();
                    requestThread = new RequestThread(handler,"register", params);
                    hilo = new Thread(requestThread);
                    hilo.start();
                }else{
                    Connection_problem.newInstance(false).show(fm, "conn_warning");
                }
            }
        });
    }

    protected void onStart(){
        super.onStart();
        MaterialTextField mName = (MaterialTextField) findViewById(R.id.materialName);
        MaterialTextField mSurname = (MaterialTextField) findViewById(R.id.materialSurname);
        MaterialTextField mUserName = (MaterialTextField) findViewById(R.id.materialUsername);
        MaterialTextField mPass = (MaterialTextField) findViewById(R.id.materialPass);
        MaterialTextField mPassConf = (MaterialTextField) findViewById(R.id.materialPassConf);
        mName.expand();
        mSurname.expand();
        mUserName.expand();
        mPass.expand();
        mPassConf.expand();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

         if(id == android.R.id.home)
        {
           this.finish();
           return true;
        }else return true;
    }

    @Override
    public boolean handleMessage(Message msg) {
        RequestResponse response = (RequestResponse) msg.obj;
        if(!response.error) {
            prefs.edit().putString("username", response.email).apply();
            prefs.edit().putBoolean("isLogged", true).apply();
            startActivity(new Intent(getApplicationContext(), CategoriesList.class));
        }
        else
            Connection_problem.newInstance(false).show(fm, "conn_error");
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
