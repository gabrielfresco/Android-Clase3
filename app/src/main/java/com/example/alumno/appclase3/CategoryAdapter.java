package com.example.alumno.appclase3;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by alumno on 08/09/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private ArrayList<Category> categories;
    private CategoryListController controller;

    public CategoryAdapter(ArrayList<Category> lista, CategoryListController controller) {
        this.categories = lista;
        this.controller = controller;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new CategoryViewHolder(v, this.controller);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category p = categories.get(position);
        holder.txtTitle.setText(p.getTitulo());
        holder.txtDescription.setText(p.getDesc());
        holder.txtId.setText(p.getId().toString());
        String url = "http://lkdml.myq-see.com/"+p.getUrl_foto();
        Glide.with(controller.getActivity()).load(url).into(holder.imageView);
    }

    public void setCategoriesList(ArrayList<Category> list){
        this.categories = list;
    }
    @Override
    public int getItemCount() {
        return categories.size();
    }
}
