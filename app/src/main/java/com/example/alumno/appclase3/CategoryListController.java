package com.example.alumno.appclase3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.TreeMap;

import static android.content.Context.MODE_PRIVATE;
import static com.example.alumno.appclase3.R.id.categories;

/**
 * Created by Liliana on 15/11/2016.
 */

public class CategoryListController implements  Handler.Callback{

    private CategoriesList act;
    private CategoryListModel model;
    private SharedPreferences prefs;
    private CategoryAdapter pAdapter;
    private Handler handler;
    private FragmentManager fm;
    private TreeMap<String, String> params;
    private RequestThread requestThread;
    Thread hilo;

    public CategoryListController(CategoriesList act, CategoryListModel model){
        this.act = act;
        handler = new Handler(this);
        this.model = model;
        this.prefs = act.getSharedPreferences("login", MODE_PRIVATE);
        pAdapter = new CategoryAdapter(model.getCategoriesList(), this);
        fm = act.getSupportFragmentManager();
        params = new TreeMap<String, String>();
        this.httpGetList();
        this.model.getRecycler().setAdapter(pAdapter);

    }

    public void setOnClickListener(View v, Integer id){
        switch (id){
            case R.id.action_search:
                final CategoryListController controller = this;
                SearchView searchView = (SearchView) v;
                  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        // Toast like print
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        ArrayList<Category> filteredList = model.getFilteredList();
                        filteredList.clear();
                        for (Category cat : model.getCategoriesList()) {
                            if (cat.getTitulo().toLowerCase().contains(s.toLowerCase()))
                                filteredList.add(cat);
                        }
                        pAdapter = new CategoryAdapter(filteredList, controller);
                        pAdapter.notifyDataSetChanged();
                        model.getRecycler().setAdapter(pAdapter);

                        return true;
                    }
                });
                break;
            case R.id.add_category:
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        act.startActivity(new Intent(act,CategoryActivity.class));
                    }
                });
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        RequestResponse response = (RequestResponse) msg.obj;
        switch (msg.arg1){
            case 1:
                if(!response.error) {
                    model.setCategoriesList(response.categorias);
                    pAdapter.setCategoriesList(model.getCategories());
                    pAdapter.notifyDataSetChanged();
                }else{
                    Connection_problem.newInstance(true).show(fm, "conn_error");
                }
                break;
            case 2:
                if(!response.error){
                    ArrayList<Category> categories = model.getCategories();
                    for (Category cat : categories) {
                        if (cat.getId().equals(msg.arg2)) {
                            categories.remove(cat);
                            model.setCategoriesList(categories);
                            pAdapter.setCategoriesList(categories);
                            pAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }else{
                    Connection_problem.newInstance(true).show(fm, "conn_error");
                }
                break;
        }
        return false;
    }

    public void httpGetList(){
        if(ConnectionUtils.isConnected(act.getApplicationContext())){
            params.put("email", this.prefs.getString("username",""));
            params.put("password", this.prefs.getString("password",""));
            requestThread = new RequestThread(handler,"getList", params);
            requestThread.setApiKey(this.prefs.getString("apiKey",""));
            hilo = new Thread(requestThread);
            hilo.start();
        }else{
            Connection_problem.newInstance(false).show(fm, "conn_warning");
        }
    }

    public boolean menuItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.settings) {
            this.prefs.edit().clear().commit();
            act.startActivity(new Intent(act, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;

        }
        else if(id == android.R.id.home)
        {
            return  true;

        }else if(id == categories){

            return true;

        }else
            return false;
    }

    public SharedPreferences getPrefs(){
        return this.prefs;
    }

     public void modifyCategory(Category cat) {
        if(ConnectionUtils.isConnected(act.getApplicationContext())){
            Intent i = new Intent(act,CategoryActivity.class);
            Gson gS = new Gson();
            String jsonCat = gS.toJson(cat);
            i.putExtra("categoryToModify", jsonCat);
            act.startActivity(i);
        }else{
            Connection_problem.newInstance(false).show(fm, "conn_warning");
        }
    }

    public void deleteCategory(String id) {
        if(ConnectionUtils.isConnected(act.getApplicationContext())){
            params.put("category_id", id);
            requestThread.setMethodParams(params);
            requestThread.setRequestMethodName("deleteCategory");
            hilo = new Thread(requestThread);
            hilo.start();
        }else{
            Connection_problem.newInstance(false).show(fm, "conn_warning");
        }
    }

    public CategoriesList getActivity() {
        return act;
    }
}
