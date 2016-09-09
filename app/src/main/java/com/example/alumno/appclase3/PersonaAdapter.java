package com.example.alumno.appclase3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;

import java.io.Console;
import java.util.ArrayList;

/**
 * Created by alumno on 08/09/2016.
 */
public class PersonaAdapter extends RecyclerView.Adapter<PersonaViewHolder> {

    private ArrayList<Persona> personas;
    private Activity activity;

    public PersonaAdapter(ArrayList<Persona> lista, Activity activity) {
        this.personas = lista;
        this.activity = activity;
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_layout, parent, false);
        return new PersonaViewHolder(v, (Recycler_view)this.activity);
    }

    @Override
    public void onBindViewHolder(PersonaViewHolder holder, int position) {
        final Persona p = personas.get(position);
        holder.txtApellido.setText(p.getApellido());
        holder.txtNombre.setText(p.getNombre());
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }
}
