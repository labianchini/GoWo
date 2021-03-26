package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
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

import com.example.gowo.R;
import com.example.gowo.adapter.MyAdapterPrest;
import com.example.gowo.model.MeusServicosViewModel;
import com.example.gowo.model.Servico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MeusServicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_servicos);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MeusServicosActivity.this, CadastrarServicoActivity.class);
                startActivity(i);
            }
        });

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        MeusServicosViewModel meusServicosViewModel = new ViewModelProvider(this, new MeusServicosViewModel.MeusServicosViewModelFactory(id)).get(MeusServicosViewModel.class);

        final RecyclerView rvMeusServicos = findViewById(R.id.rvMeusServicos);
        rvMeusServicos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMeusServicos.setLayoutManager(layoutManager);

        final LiveData<List<Servico>> meusServicos = meusServicosViewModel.getMeusServicos();
        meusServicos.observe(this, new Observer<List<Servico>>() {
            @Override
            public void onChanged(List<Servico> servicos) {
                MyAdapterPrest myAdapterPrest = new MyAdapterPrest(MeusServicosActivity.this, servicos); //A mainActivity é avisada que chegou uma nova lista
                rvMeusServicos.setAdapter(myAdapterPrest);  //A interface é atualizada
            }
        });
    }
}