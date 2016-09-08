package com.example.alumno.appclase3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
        personas.add(new Persona("Gabriel", "Frecso"));
        personas.add(new Persona("Juan", "Gomez"));
        personas.add(new Persona("Diego", "Milito"));
        personas.add(new Persona("Otro", "Tipo"));
        personas.add(new Persona("Probando", "Nuevo"));
        personas.add(new Persona("ASD", "OOO"));
        personas.add(new Persona("Manu ", "Ginobili"));
        personas.add(new Persona("Luis", "Scola"));
        personas.add(new Persona("Mas", "Personas"));

        pAdapter = new PersonaAdapter(personas);

        recyclerPersonas.setAdapter(pAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerPersonas.setLayoutManager(layoutManager);


    }

}
