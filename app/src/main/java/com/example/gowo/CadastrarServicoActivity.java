package com.example.gowo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class CadastrarServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar); //Aqui é definido que o ActionBar da Activity é o que foi selecionado na linha de cima

        ActionBar actionBar = getSupportActionBar(); //Botão de voltar para a MainActivity
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}