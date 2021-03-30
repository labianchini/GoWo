package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.gowo.R;

public class CadastrarEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_endereco);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Spinner spinnerEstados = findViewById(R.id.spnrEst);
        spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String est = spinnerEstados.getItemAtPosition(position).toString();
                Log.d("estado", est);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        //String[] lsEstado = getResources().getStringArray(R.array.siglas);
        //spinnerEstados.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_cadastrar_endereco, lsEstado));*/

        /*Button btnAddEnd = findViewById(R.id.btnAddEnd);
        btnAddEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posicao = spinnerEstados.ge();
            }
        });*/
    }
}