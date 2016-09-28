package com.example.alumno.appclase3;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by alumno on 08/09/2016.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder{
    public TextView txtTitle;
    public TextView txtDescription;

    public ImageView imageView;
    public CategoryViewHolder(View v, final Activity activity) {
        super(v);


        txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        imageView = (ImageView) v.findViewById(R.id.categoryImage);

        final CategoriesList act = (CategoriesList)activity;
        Glide.with(act).load("https://s-media-cache-ak0.pinimg.com/originals/09/7c/7c/097c7c15103d99cb550b364ea5fdb4bc.jpg").into(imageView);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.modifyCategory(new Category(txtTitle.getText().toString(), txtDescription.getText().toString(),false, ""));
            }
        });
    }
}
