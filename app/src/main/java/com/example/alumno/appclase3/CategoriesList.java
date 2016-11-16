package com.example.alumno.appclase3;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;

public class CategoriesList extends AppCompatActivity implements  Connection_problem.OnFragmentInteractionListener{

    private CategoryListController controller;
    private CategoryListModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler_view);
        model = new CategoryListModel(this);
        controller = new CategoryListController(this,model);
        model.setController(controller);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.listTitle) + " " + controller.getPrefs().getString("username", ""));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_layout; this adds items to the action bar if it is present.
        model.setSearchBarListener(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return controller.menuItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        controller.httpGetList();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
