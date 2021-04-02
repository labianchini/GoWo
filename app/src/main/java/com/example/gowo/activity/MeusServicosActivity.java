package com.example.gowo.activity;

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

import com.example.gowo.R;
import com.example.gowo.adapter.MyAdapterServUser;
import com.example.gowo.model.MeusServicosViewModel;
import com.example.gowo.model.Servico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MeusServicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        final String id= i.getStringExtra("iduserLog");

        Button BtnAddServ = findViewById(R.id.BtnAddServ);
        BtnAddServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MeusServicosActivity.this, CadastrarServicoActivity.class);
                startActivity(i);
            }
        });

        MeusServicosViewModel meusServicosViewModel = new ViewModelProvider(this, new MeusServicosViewModel.MeusServicosViewModelFactory(id)).get(MeusServicosViewModel.class);

        final RecyclerView rvMeusServicos = findViewById(R.id.rvMeusEnd);
        rvMeusServicos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMeusServicos.setLayoutManager(layoutManager);

        final LiveData<List<Servico>> meusServicos = meusServicosViewModel.getMeusServicos();
        meusServicos.observe(this, new Observer<List<Servico>>() {
            @Override
            public void onChanged(List<Servico> servicos) {
                MyAdapterServUser myAdapterServUser = new MyAdapterServUser(MeusServicosActivity.this, servicos); //A mainActivity é avisada que chegou uma nova lista
                rvMeusServicos.setAdapter(myAdapterServUser);  //A interface é atualizada
            }
        });
    }
}