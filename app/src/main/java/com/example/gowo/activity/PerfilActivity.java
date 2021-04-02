package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gowo.R;
import com.example.gowo.model.PerfilViewModel;
import com.example.gowo.util.Config;
import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfilActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    String idUserLog;
    String nomeUser;
    String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        BottomNavigationView navigationView = findViewById(R.id.toolbarhome);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.perfil);

        Intent i = getIntent();
        this.idUserLog = i.getStringExtra("iduserLog");
        this.nomeUser = i.getStringExtra("nomeUser");
        this.emailUser = i.getStringExtra("emailUser");

        PerfilViewModel perfilViewModel = new ViewModelProvider(this, new PerfilViewModel.PerfilViewModelFactory(idUserLog)).get(PerfilViewModel.class);

        String imgBase64 = perfilViewModel.getImagem();
        String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
        Bitmap img = Util.base642Bitmap(pureBase64Encoded);

        ImageView imgViewIconeUser = findViewById(R.id.imgViewIconeUser);
        imgViewIconeUser.setImageBitmap(img);

        TextView txtViewNomeUser = findViewById(R.id.txtViewNomeUser);
        txtViewNomeUser.setText(nomeUser);

        Button btnInfPessoal = findViewById(R.id.btnInfPessoal);
        btnInfPessoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, InfoPessoaisActivity.class);
                i.putExtra("emailUser", emailUser);
                startActivity(i);
            }
        });

        Button btnMeusServicos = findViewById(R.id.btnMeusServicos);
        btnMeusServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, MeusServicosActivity.class);
                i.putExtra("iduserLog", idUserLog);
                startActivity(i);
            }
        });
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
            case R.id.endereco:
                Intent i = new Intent(PerfilActivity.this, MeusEnderecosActivity.class);
                i.putExtra("iduserLog", idUserLog);
                i.putExtra("nomeUser", nomeUser);
                i.putExtra("emailUser", emailUser);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}