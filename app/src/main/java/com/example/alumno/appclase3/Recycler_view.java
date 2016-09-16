package com.example.alumno.appclase3;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.support.v7.app.ActionBar;

public class Recycler_view extends AppCompatActivity {
    private RecyclerView recyclerPersonas;
    private PersonaAdapter pAdapter;
    private ArrayList<Persona> personas;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.categories_list_tittle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        recyclerPersonas = (RecyclerView) findViewById(R.id.recycler_personas);

        personas = new ArrayList<>();
        personas.add(new Persona("Gabriel", "Frecso", "112312312"));
        personas.add(new Persona("Juan", "Gomez", "112312312"));
        personas.add(new Persona("Diego", "Milito", "112312312"));
        personas.add(new Persona("Otro", "Tipo", "112312312"));
        personas.add(new Persona("Probando", "Nuevo", "112312312"));
        personas.add(new Persona("ASD", "OOO", "112312312"));
        personas.add(new Persona("Manu ", "Ginobili", "112312312"));
        personas.add(new Persona("Luis", "Scola", "112312312"));
        personas.add(new Persona("Mas", "Personas", "112312312"));

        pAdapter = new PersonaAdapter(personas, this);

        recyclerPersonas.setAdapter(pAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerPersonas.setLayoutManager(layoutManager);


    }

    public void onClickCall(String tel) {
            Log.d("En el mail", "Entra");
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+tel ));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                Log.d("Sin Permisos", "AAA");
                return;
            }
            this.startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_layout; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
            this.prefs.edit().clear().commit();
            this.finish();
            return  true;
        }

        else if(id == android.R.id.home)
        {
            this.startActivity(new Intent(this, MainActivity.class));
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }

}
