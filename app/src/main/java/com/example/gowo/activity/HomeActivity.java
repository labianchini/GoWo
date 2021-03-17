package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gowo.R;
import com.example.gowo.util.Config;
import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView = findViewById(R.id.toolbarhome);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.home);

        /*final String login = Config.getEmail(HomeActivity.this);
        final String password = Config.getSenha(HomeActivity.this);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/", "POST", "UTF-8");
                httpRequest.addParam("login", login);
                httpRequest.addParam("password", password);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dados do usuário para informaçoes pessoais
                            }
                        });
                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HomeActivity.this, error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/

        ImageButton imgBtnLimpeza = findViewById(R.id.imgBtnLimpeza);
        imgBtnLimpeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "limpeza";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnJardinagem = findViewById(R.id.imgBtnJardinagem);
        imgBtnJardinagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "jardinagem";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnFotografia = findViewById(R.id.imgBtnFotografia);
        imgBtnFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "fotografia";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnAutomotivo = findViewById(R.id.imgBtnAutomotivo);
        imgBtnAutomotivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "automotivo";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnComida = findViewById(R.id.imgBtnComida);
        imgBtnComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "comida";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnInformatica = findViewById(R.id.imgBtnInformatica);
        imgBtnInformatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "informatica";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnCostura = findViewById(R.id.imgBtnCostura);
        imgBtnCostura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "costura";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnPet = findViewById(R.id.imgBtnPet);
        imgBtnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "pet";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnReforma = findViewById(R.id.imgBtnReforma);
        imgBtnReforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "reforma";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnBeleza = findViewById(R.id.imgBtnBeleza);
        imgBtnBeleza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "beleza";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.home:
                    Intent i = new Intent(HomeActivity.this,HomeActivity.class);
                    startActivity(i);
                default:
                    super.onOptionsItemSelected(item);
            }
        switch (item.getItemId()){
            case R.id.recentes:
                Intent i = new Intent(HomeActivity.this, RecentesActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(HomeActivity.this, PerfilActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}