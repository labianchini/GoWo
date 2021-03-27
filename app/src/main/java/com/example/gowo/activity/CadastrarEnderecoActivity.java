package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gowo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CadastrarEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_endereco);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*Spinner spinnerEstados = findViewById(R.id.spnrEst);
        String[] lsEstado = getResources().getStringArray(R.array.siglas);
        spinnerEstados.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_cadastrar_endereco, lsEstado));*/

    }

}
