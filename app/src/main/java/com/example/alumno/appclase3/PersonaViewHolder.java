package com.example.alumno.appclase3;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by alumno on 08/09/2016.
 */
public class PersonaViewHolder extends RecyclerView.ViewHolder{
    public TextView txtNombre;
    public TextView txtApellido;

    public PersonaViewHolder(View v) {
        super(v);
        txtNombre = (TextView) v.findViewById(R.id.name);
        txtApellido = (TextView) v.findViewById(R.id.surname);


    }
}
