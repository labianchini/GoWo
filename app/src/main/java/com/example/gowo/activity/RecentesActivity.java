package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gowo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecentesActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentes);

        BottomNavigationView navigationView = findViewById(R.id.toolbarhome);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.recentes);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent i = new Intent(RecentesActivity.this,HomeActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.recentes:
                Intent i = new Intent(RecentesActivity.this, RecentesActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(RecentesActivity.this, PerfilActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}