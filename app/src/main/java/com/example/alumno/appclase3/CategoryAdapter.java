package com.example.alumno.appclase3;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by alumno on 08/09/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private ArrayList<Category> categories;
    private Activity activity;

    public CategoryAdapter(ArrayList<Category> lista, Activity activity) {
        this.categories = lista;
        this.activity = activity;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new CategoryViewHolder(v, this.activity);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category p = categories.get(position);
        holder.txtTitle.setText(p.getTitle());
        holder.txtDescription.setText(p.getDescription());
    }

    public void setCategoriesList(ArrayList<Category> list){
        this.categories = list;
    }
    @Override
    public int getItemCount() {
        return categories.size();
    }
}
