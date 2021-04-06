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
import com.example.gowo.model.Usuario;
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

    Usuario usuarioLogado;

    public void HomeActivity(Usuario usuarioLogado){
        this.usuarioLogado = usuarioLogado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView = findViewById(R.id.toolbarhome);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.home);

        final String login = Config.getEmail(HomeActivity.this);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_return_user_data_from_email.php", "GET", "UTF-8");
                httpRequest.addParam("email", login);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        final String idUsu = jsonObject.getString("idUser");
                        final String nomeUsu = jsonObject.getString("usrName");
                        final String sobrenomeUsu = jsonObject.getString("usrLastName");
                        final String emailUsu = jsonObject.getString("usrEmail");
                        final String dataNUsu = jsonObject.getString("usrDateN");
                        final String telefoneUsu = jsonObject.getString("usrCellPhone");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                usuarioLogado = new Usuario();
                                usuarioLogado.setIdUsu(idUsu);
                                usuarioLogado.setNameUsu(nomeUsu);
                                usuarioLogado.setSobrenomeUsu(sobrenomeUsu);
                                usuarioLogado.setEmailUsu(emailUsu);
                                usuarioLogado.setDataNascUsu(dataNUsu);
                                usuarioLogado.setTelefoneUsu(telefoneUsu);
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
        });

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
                String categoria = "corte e costura";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnPet = findViewById(R.id.imgBtnPet);
        imgBtnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "cuidados pet";
                i.putExtra("categoria", categoria);
                startActivity(i);
            }
        });
        ImageButton imgBtnReforma = findViewById(R.id.imgBtnReforma);
        imgBtnReforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, FeedCategoriaActivity.class);
                String categoria = "reformas e consertos";
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
            case R.id.endereco:
                Intent i = new Intent(HomeActivity.this, MeusEnderecosActivity.class);
                i.putExtra("iduserLog", usuarioLogado.getIdUsu());
                i.putExtra("nomeUser", usuarioLogado.getNameUsu() + " " + usuarioLogado.getSobrenomeUsu());
                i.putExtra("emailUser", usuarioLogado.getEmailUsu());
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(HomeActivity.this, PerfilActivity.class);
                i.putExtra("iduserLog", usuarioLogado.getIdUsu());
                i.putExtra("nomeUser", usuarioLogado.getNameUsu() + " " + usuarioLogado.getSobrenomeUsu());
                i.putExtra("emailUser", usuarioLogado.getEmailUsu());
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}