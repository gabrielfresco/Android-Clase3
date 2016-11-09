package com.example.alumno.appclase3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

public class CategoriesList extends AppCompatActivity implements Handler.Callback {
    private RecyclerView recyclerCategories;
    private CategoryAdapter pAdapter;
    private ArrayList<Category> categories;
    private ArrayList<Category> filteredList;
    private Activity currentActivity;
    SharedPreferences prefs;
    Thread hilo;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);

        currentActivity = this;
        actionBar.setTitle(getString(R.string.listTitle) + " " + prefs.getString("username", ""));
        recyclerCategories = (RecyclerView) findViewById(R.id.recycler_personas);

        filteredList = new ArrayList<>();
        categories = new ArrayList<>();


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

        handler = new Handler(this);
        hilo = new Thread(new RequestThread(handler,""));
        hilo.start();

    }

    public void modifyCategory(Category cat) {
            Intent i = new Intent(this,CategoryActivity.class);
            Gson gS = new Gson();
            String jsonCat = gS.toJson(cat);
            i.putExtra("categoryToModify", jsonCat);
            this.startActivity(i);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_layout; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                filteredList.clear();
                for (Category cat : categories) {
                    if (cat.getTitle().toLowerCase().contains(s.toLowerCase()))
                        filteredList.add(cat);
                }
                pAdapter = new CategoryAdapter(filteredList, currentActivity);
                pAdapter.notifyDataSetChanged();
                recyclerCategories.setAdapter(pAdapter);

                return true;
            }
        });

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


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.arg1){
            case 1:
                pAdapter.setCategoriesList((ArrayList<Category>)msg.obj);
                pAdapter.notifyDataSetChanged();
                break;
        }
        return false;
    }

}
