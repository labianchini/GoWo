package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gowo.R;
import com.example.gowo.util.Config;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        BottomNavigationView navigationView = findViewById(R.id.toolbarhome);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.perfil);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                Intent i = new Intent(PerfilActivity.this,HomeActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.recentes:
                Intent i = new Intent(PerfilActivity.this, RecentesActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(PerfilActivity.this, PerfilActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}