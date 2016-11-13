package com.example.alumno.appclase3;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by alumno on 08/09/2016.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder{
    public TextView txtTitle;
    public TextView txtDescription;
    public TextView txtId;
    public ImageView imageView;
    public ImageButton delete;
    public ImageButton modify;
    public CategoryViewHolder(View v, final Activity activity) {
        super(v);

        txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        txtId = (TextView) v.findViewById(R.id.category_id_list);
        imageView = (ImageView) v.findViewById(R.id.categoryImage);
        delete = (ImageButton) v.findViewById(R.id.delete_category);
        modify = (ImageButton) v.findViewById(R.id.modify_category);

        final CategoriesList act = (CategoriesList)activity;
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.modifyCategory(new Category(txtTitle.getText().toString(), txtDescription.getText().toString(),"",Integer.parseInt(txtId.getText().toString())));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.deleteCategory(txtId.getText().toString());
            }
        });
    }
}
