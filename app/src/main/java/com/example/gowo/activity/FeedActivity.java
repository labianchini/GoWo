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
import android.view.View;
import android.widget.Button;

import com.example.gowo.model.Servico;
import com.example.gowo.adapter.MyAdapter;
import com.example.gowo.model.FeedViewModel;
import com.example.gowo.R;

import java.util.List;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        final RecyclerView rvServicos = findViewById(R.id.rvServicos);
        rvServicos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvServicos.setLayoutManager(layoutManager);

        FeedViewModel feedViewModel = new ViewModelProvider(this).get(FeedViewModel.class);
        LiveData<List<Servico>> servicos = feedViewModel.getServicos();   // Livedata- cria uma lista com os serviços que pode ser observada, mas não alterada
        servicos.observe(this, new Observer<List<Servico>>() {     //Funcão que vai observar se a lista mudou, e se mudou ela vai ser atualizada
            @Override
            public void onChanged(List<Servico> servicos) {
                MyAdapter myAdapter = new MyAdapter(FeedActivity.this, servicos); //A mainActivity é avisada que chegou uma nova lista
                rvServicos.setAdapter(myAdapter);  //A interface é atualizada
            }
        });

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

    }
}