package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gowo.R;

public class FavoritosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar toolbarmain = findViewById(R.id.toolbarFavoritos);
        setSupportActionBar(toolbarmain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // para o menu aparecer na toolbar
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  // funcão quando apertar em cada item
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.home:
                Intent i = new Intent(FavoritosActivity.this, HomeActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.favoritos:
                Intent i = new Intent(FavoritosActivity.this, FavoritosActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(FavoritosActivity.this, PerfilActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}