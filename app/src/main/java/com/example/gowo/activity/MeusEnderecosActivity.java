package com.example.gowo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gowo.R;
import com.example.gowo.adapter.MyAdapterEndUser;
import com.example.gowo.adapter.MyAdapterServUser;
import com.example.gowo.model.Endereco;
import com.example.gowo.model.MeusEnderecosViewModel;
import com.example.gowo.model.MeusServicosViewModel;
import com.example.gowo.model.Servico;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MeusEnderecosActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    String idUserLog;
    String nomeUser;
    String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_enderecos);

        Intent i = getIntent();
        this.idUserLog = i.getStringExtra("iduserLog");
        this.nomeUser = i.getStringExtra("nomeUser");
        this.emailUser = i.getStringExtra("emailUser");

        BottomNavigationView navigationView = findViewById(R.id.toolbarhome);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.endereco);

        Button btnAddEnd = findViewById(R.id.btnAddEnd);
        btnAddEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MeusEnderecosActivity.this, CadastrarEnderecoActivity.class);
                i.putExtra("iduserLog", idUserLog);
                startActivity(i);
            }
        });

        MeusEnderecosViewModel meusEnderecosViewModel = new ViewModelProvider(this, new MeusEnderecosViewModel.MeusEnderecosViewModelFactory(idUserLog)).get(MeusEnderecosViewModel.class);

        final RecyclerView rvMeusEnd = findViewById(R.id.rvMeusEnd);
        rvMeusEnd.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMeusEnd.setLayoutManager(layoutManager);

        final LiveData<List<Endereco>> meusEnderecos = meusEnderecosViewModel.getMeusEnderecos();
        meusEnderecos.observe(this, new Observer<List<Endereco>>() {
            @Override
            public void onChanged(List<Endereco> enderecos) {
                MyAdapterEndUser myAdapterEndUser = new MyAdapterEndUser(MeusEnderecosActivity.this, enderecos); //A mainActivity é avisada que chegou uma nova lista
                rvMeusEnd.setAdapter(myAdapterEndUser);  //A interface é atualizada
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent i = new Intent(MeusEnderecosActivity.this,HomeActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(MeusEnderecosActivity.this, PerfilActivity.class);
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