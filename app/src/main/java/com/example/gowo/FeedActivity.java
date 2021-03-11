package com.example.gowo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        RecyclerView rvServicos = findViewById(R.id.rvServicos);
        rvServicos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvServicos.setLayoutManager(layoutManager);

        FeedViewModel feedViewModel = new ViewModelProvider(this).get(FeedViewModel.class);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btnVerMais1 = findViewById(R.id.btnVerMais1);
        btnVerMais1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FeedActivity.this, ServicoActivity.class);
                startActivity(i);
            }
        });
        Button btnVerMais2 = findViewById(R.id.btnVerMais2);
        btnVerMais2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FeedActivity.this, ServicoActivity.class);
                startActivity(i);
            }
        });

    }
}