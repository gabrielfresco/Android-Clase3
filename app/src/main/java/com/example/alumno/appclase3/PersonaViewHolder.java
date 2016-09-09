package com.example.alumno.appclase3;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by alumno on 08/09/2016.
 */
public class PersonaViewHolder extends RecyclerView.ViewHolder{
    public TextView txtNombre;
    public TextView txtApellido;
    public ImageButton btnCall;

    public PersonaViewHolder(View v, final Recycler_view activity) {
        super(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onClickCall("12345");
            }
        });

        txtNombre = (TextView) v.findViewById(R.id.name);
        txtApellido = (TextView) v.findViewById(R.id.surname);
        btnCall = (ImageButton) v.findViewById(R.id.call);
    }
}
