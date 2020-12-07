package com.example.gowo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbarmain = findViewById(R.id.toolbarHome);  // tornar a toolbar principal
        setSupportActionBar(toolbarmain);

        /*MyAdapter myAdapter = new MyAdapter(this);
        RecyclerView rvFeed = findViewById(R.id.rvFeed);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFeed.setLayoutManager(layoutManager);
        rvFeed.setAdapter(myAdapter);*/
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
                Intent i = new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.chat:
                Intent i = new Intent(HomeActivity.this,ChatActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(HomeActivity.this,PerfilActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}
