package com.example.alumno.appclase3;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Liliana on 15/11/2016.
 */

public class CategoryListModel {
    private CategoryListController controller;
    private CategoriesList act;
    private RecyclerView recyclerCategories;
    private ArrayList<Category> categories;
    private ArrayList<Category> filteredList;
    private FloatingActionButton newCategory;

    public CategoryListModel(CategoriesList act) {
        this.act = act;
        this.recyclerCategories = (RecyclerView) act.findViewById(R.id.recycler_personas);
        categories = new ArrayList<>();
        filteredList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(act);
        recyclerCategories.setLayoutManager(layoutManager);
        newCategory = (FloatingActionButton) act.findViewById(R.id.add_category);
    }

    public void setController(CategoryListController controller){
        this.controller = controller;
    }

    public ArrayList<Category> getCategoriesList() {
        return categories;
    }

    public ArrayList<Category> getFilteredList() {
        return filteredList;
    }

    public void setSearchBarListener(Menu menu){
        act.getMenuInflater().inflate(R.menu.menu_layout, menu);
        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        controller.setOnClickListener(searchView,R.id.action_search);
        controller.setOnClickListener(newCategory, R.id.add_category);
    }

    public RecyclerView getRecycler() {
        return recyclerCategories;
    }

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categories = categoriesList;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
