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
public class CategoryViewHolder extends RecyclerView.ViewHolder{
    public TextView txtNombre;
    public TextView txtApellido;
    public ImageButton btnCall;
    public CategoryViewHolder(View v, final Activity activity) {
        super(v);
        final Recycler_view act = (Recycler_view)activity;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Metodo click de la view", "entra");
                act.modifyCategory(new Category("adsad","adas","sada"));
            }
        });

        txtNombre = (TextView) v.findViewById(R.id.name);
        txtApellido = (TextView) v.findViewById(R.id.surname);
        btnCall = (ImageButton) v.findViewById(R.id.call);
    }
}