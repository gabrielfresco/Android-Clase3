package com.example.alumno.appclase3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by alumno on 08/09/2016.
 */
public class PersonaAdapter extends RecyclerView.Adapter<PersonaViewHolder> {

    private ArrayList<Persona> personas;

    public PersonaAdapter(ArrayList<Persona> lista){
        this.personas = lista;
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_layout, parent, false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonaViewHolder holder, int position) {
        Persona p = personas.get(position);
        holder.txtApellido.setText(p.getApellido());
        holder.txtNombre.setText(p.getNombre());
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }
}
