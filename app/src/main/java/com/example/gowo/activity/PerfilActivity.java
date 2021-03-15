package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gowo.R;
import com.example.gowo.util.Config;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Toolbar toolbar = findViewById(R.id.toolbarPerfil);  // tornar a toolbar principal
        setSupportActionBar(toolbar);

        Button btnInfPessoal = findViewById(R.id.btnInfPessoal);
        btnInfPessoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, InfoPessoaisActivity.class);
                startActivity(i);
            }
        });

        Button btnMeusServicos = findViewById(R.id.btnMeusServicos);
        btnMeusServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, MeusServicosActivity.class);
                startActivity(i);
            }
        });
        // não sei se a função de sair está certa
        Button btnSair = findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.setEmail(PerfilActivity.this, "");
                Config.setSenha(
                        PerfilActivity.this, "");
                Intent i = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
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
                Intent i = new Intent(PerfilActivity.this, HomeActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.favoritos:
                Intent i = new Intent(PerfilActivity.this, FavoritosActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(PerfilActivity.this,PerfilActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}