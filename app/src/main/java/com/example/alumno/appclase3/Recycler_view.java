package com.example.alumno.appclase3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Recycler_view extends AppCompatActivity {
    private RecyclerView recyclerPersonas;
    private PersonaAdapter pAdapter;
    private ArrayList<Persona> personas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            this.startActivity(intent);
        }


}
