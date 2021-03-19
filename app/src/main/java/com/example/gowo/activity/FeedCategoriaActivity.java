package com.example.gowo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gowo.adapter.MyAdapterFeed;
import com.example.gowo.model.FeedCategoriaViewModel;
import com.example.gowo.R;
import com.example.gowo.model.Usuario;

import java.util.List;

public class FeedCategoriaActivity extends AppCompatActivity {

    static int ADD_PRODUCT_ACTIVITY_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_categoria);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String categoria = i.getStringExtra("categoria");
        String categoria_maisc = categoria.substring(0,1).toUpperCase() + categoria.substring(1);

        Log.d("categoria", categoria);

        MyAdapterFeed myAdapterFeed = new MyAdapterFeed(FeedCategoriaActivity.this, categoria_maisc);

        TextView txtcategoria = findViewById(R.id.txtcategoria);
        txtcategoria.setText(categoria_maisc);

        FeedCategoriaViewModel feedCategoriaViewModel = new ViewModelProvider(this, new FeedCategoriaViewModel.FeedCategoriaViewModelFactory(categoria)).get(FeedCategoriaViewModel.class);

        final RecyclerView rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUsuarios.setLayoutManager(layoutManager);

        final LiveData<List<Usuario>> usuarios = feedCategoriaViewModel.getUsuarios();   // Livedata- cria uma lista com os usuarios que pode ser observada, mas não alterada
        usuarios.observe(this, new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {
                MyAdapterFeed myAdapterFeed = new MyAdapterFeed(FeedCategoriaActivity.this, usuarios); //A mainActivity é avisada que chegou uma nova lista
                rvUsuarios.setAdapter(myAdapterFeed);  //A interface é atualizada
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCT_ACTIVITY_RESULT){
            if(resultCode == Activity.RESULT_OK){
                FeedCategoriaViewModel feedCategoriaViewModel = new ViewModelProvider(this).get(FeedCategoriaViewModel.class);
                feedCategoriaViewModel.refreshUsuarios();
            }
        }
    }
}