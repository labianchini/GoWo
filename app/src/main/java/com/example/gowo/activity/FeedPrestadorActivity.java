package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gowo.R;
import com.example.gowo.adapter.MyAdapterFeed;
import com.example.gowo.adapter.MyAdapterPrest;
import com.example.gowo.model.FeedPrestadorViewModel;
import com.example.gowo.model.Servico;
import com.example.gowo.model.Usuario;
import com.example.gowo.model.ViewServicoViewModel;

import java.util.List;

public class FeedPrestadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_prestador);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        String categoria = i.getStringExtra("categoria");

        FeedPrestadorViewModel feedPrestadorViewModel = new ViewModelProvider(this, new FeedPrestadorViewModel.FeedPrestadorViewModelFactory(id, categoria)).get(FeedPrestadorViewModel.class);

        final RecyclerView rvServUsu = findViewById(R.id.rvServUsu);
        rvServUsu.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvServUsu.setLayoutManager(layoutManager);

        final LiveData<List<Servico>> servicos = feedPrestadorViewModel.getServicos();   // Livedata- cria uma lista com os servicos que pode ser observada, mas não alterada
        servicos.observe(this, new Observer<List<Servico>>() {
            @Override
            public void onChanged(List<Servico> servicos) {
                MyAdapterPrest myAdapterPrest = new MyAdapterPrest(FeedPrestadorActivity.this, servicos); //A mainActivity é avisada que chegou uma nova lista
                rvServUsu.setAdapter(myAdapterPrest);  //A interface é atualizada
            }
        });
    }
}