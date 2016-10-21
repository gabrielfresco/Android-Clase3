package com.example.alumno.appclase3;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;

public class CategoryActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private static final int CAMERA_REQUEST = 1;
    private ImageButton photoButton;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_category);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Categoria");
        actionBar.setDisplayHomeAsUpEnabled(true);

        String target = getIntent().getStringExtra("categoryToModify");
        Category cat = new Gson().fromJson(target, Category.class);

        if(cat != null){
            ((TextView)findViewById(R.id.txtCategoryTitle)).setText(R.string.categoryModifyTitle);
            ((EditText)findViewById(R.id.categoryName)).setText(cat.getTitle());
            ((EditText)findViewById(R.id.categoryDescription)).setText(cat.getDescription());
        }

        ((Button)findViewById(R.id.saveCategory)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO guardar la entidad
                finish();
            }
        });

        photoButton = (ImageButton) this.findViewById(R.id.categoryPictureButton);

        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File imagesFolder = new File(Environment.getExternalStorageDirectory(),"Imagenes Categories Manager");
                imagesFolder.mkdirs();
                File img = new File(imagesFolder,"myImage.jpg");
                uri = Uri.fromFile(img);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                else
                    ActivityCompat.requestPermissions(CategoryActivity.this,new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
               photoButton.setImageURI(uri);
        }
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
            this.finish();
            return true;
        }else if(id == R.id.categories){
            if(!(getApplicationContext() instanceof CategoriesList))
                this.startActivity(new Intent(this, CategoriesList.class));
            return true;

        }else
            return super.onOptionsItemSelected(item);
    }

}
