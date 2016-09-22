package com.example.alumno.appclase3;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.support.v7.app.ActionBar;
import android.view.View;

public class Recycler_view extends AppCompatActivity {
    private RecyclerView recyclerCategories;
    private CategoryAdapter pAdapter;
    private ArrayList<Category> categories;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);


        actionBar.setTitle(getString(R.string.listTitle) + prefs.getString("username", ""));
        recyclerCategories = (RecyclerView) findViewById(R.id.recycler_personas);

        categories = new ArrayList<>();
        categories.add(new Category("Gabriel", "Frecso", "112312312"));
        categories.add(new Category("Juan", "Gomez", "112312312"));
        categories.add(new Category("Diego", "Milito", "112312312"));
        categories.add(new Category("Otro", "Tipo", "112312312"));
        categories.add(new Category("Probando", "Nuevo", "112312312"));
        categories.add(new Category("ASD", "OOO", "112312312"));
        categories.add(new Category("Manu ", "Ginobili", "112312312"));
        categories.add(new Category("Luis", "Scola", "112312312"));
        categories.add(new Category("Mas", "Personas", "112312312"));

        pAdapter = new CategoryAdapter(categories, this);

        recyclerCategories.setAdapter(pAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerCategories.setLayoutManager(layoutManager);

        FloatingActionButton newCategory = (FloatingActionButton) findViewById(R.id.add_category);
        if (newCategory != null) {
            newCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getApplicationContext().startActivity(new Intent(getApplicationContext(),CategoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
        }


    }

    public void modifyCategory(Category cat) {

            Intent intent = new Intent(this,CategoryActivity.class);
            this.startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_layout; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            this.prefs.edit().clear().commit();
            this.startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;

        }
        else if(id == android.R.id.home)
        {
            return  true;

        }else if(id == R.id.categories){

            return true;

        }else
            return super.onOptionsItemSelected(item);
    }

}
