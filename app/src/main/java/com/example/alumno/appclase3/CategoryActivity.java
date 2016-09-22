package com.example.alumno.appclase3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class CategoryActivity extends AppCompatActivity {
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_category);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Categoria");
        actionBar.setDisplayHomeAsUpEnabled(true);

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
            this.prefs = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
            this.prefs.edit().clear().commit();
            this.startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;
        }

        else if(id == android.R.id.home)
        {
            this.startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;
        }else if(id == R.id.categories){
            if(!(getApplicationContext() instanceof Recycler_view))
                this.startActivity(new Intent(this, Recycler_view.class));
            return true;

        }else
            return super.onOptionsItemSelected(item);
    }

}
