package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.gowo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView = findViewById(R.id.toolbarhome);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.home);

        /*final String login = Config.getLogin(HomeActivity.this);
        final String password = Config.getPassword(HomeActivity.this);

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
                                //dados do usuário
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
                startActivity(i);
            }
        });
        ImageButton imgBtnFotografia = findViewById(R.id.imgBtnFotografia);
        imgBtnFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgBtnGerais = findViewById(R.id.imgBtnAutomotivo);
        imgBtnGerais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgBtnComida = findViewById(R.id.imgBtnComida);
        imgBtnComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgBtnInformatica = findViewById(R.id.imgBtnInformatica);
        imgBtnInformatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgBtnCostura = findViewById(R.id.imgBtnCostura);
        imgBtnCostura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgBtnPet = findViewById(R.id.imgBtnPet);
        imgBtnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgBtnReforma = findViewById(R.id.imgBtnReforma);
        imgBtnReforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                startActivity(i);
            }
        });
        ImageButton imgBtnMecanica = findViewById(R.id.imgBtnBeleza);
        imgBtnMecanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
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
            case R.id.favoritos:
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