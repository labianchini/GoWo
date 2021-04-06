package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gowo.R;
import com.example.gowo.model.PerfilViewModel;
import com.example.gowo.util.Config;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        final MutableLiveData<Bitmap> img = perfilViewModel.getImagem();
        img.observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                ImageView imgViewIconeUser = findViewById(R.id.imgViewIconeUser);
                imgViewIconeUser.setImageBitmap(img.getValue());
            }
        });

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