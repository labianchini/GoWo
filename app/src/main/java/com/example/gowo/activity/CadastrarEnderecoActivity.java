package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gowo.R;
import com.example.gowo.util.Config;
import com.example.gowo.util.HttpRequest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_endereco);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnAddEnd = findViewById(R.id.btnAddEnd);
        btnAddEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Spinner spinnerEstados = findViewById(R.id.spnrEst);
                spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String est = spinnerEstados.getItemAtPosition(position).toString();
                        Log.d("estado", est);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(CadastrarEnderecoActivity.this, "Campo de estado não selecionado", Toast.LENGTH_LONG).show();
                    }
                });

                EditText cepEnd =  findViewById(R.id.cepEnd);
                final String cep = cepEnd.getText().toString();
                if(cep.isEmpty()) {
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de cep não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText CidadeEnd =  findViewById(R.id.CidadeEnd);
                final String cidade = CidadeEnd.getText().toString();
                if(cidade.isEmpty()) {
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de cidade não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText bairroEnd =  findViewById(R.id.bairroEnd);
                final String bairro = bairroEnd.getText().toString();
                if(bairro.isEmpty()) {
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de bairro não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText ruaEnd =  findViewById(R.id.ruaEnd);
                final String rua = ruaEnd.getText().toString();
                if(rua.isEmpty()) {
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de rua não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText numEnd =  findViewById(R.id.numEnd);
                final String num = numEnd.getText().toString();
                if(num.isEmpty()) {
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de num não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText compEnd =  findViewById(R.id.compEnd);
                final String complemento = compEnd.getText().toString();
                if(complemento.isEmpty()) {
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de complemento não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText apelEnd =  findViewById(R.id.apelEnd);
                final String apelido = apelEnd.getText().toString();
                if(apelido.isEmpty()) {
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de apelido não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_insert_address.php")
                    }
                });
            }
        });
    }
}