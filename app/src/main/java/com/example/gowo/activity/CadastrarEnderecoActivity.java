package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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
import com.example.gowo.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarEnderecoActivity extends AppCompatActivity {

    String est;
    String iduserLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_endereco);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        this.iduserLog = i.getStringExtra("iduserLog");

        final Spinner spinnerEstados = findViewById(R.id.spnrEst);
        spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                est = spinnerEstados.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Button btnAddEnd = findViewById(R.id.btnAddEnd);
        btnAddEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String estado = est;
                if (estado=="Estado"){
                    Toast.makeText(CadastrarEnderecoActivity.this, "Campo de estado não selecionado", Toast.LENGTH_LONG).show();
                }

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
                        HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_insert_address.php", "POST", "UTF-8");
                        httpRequest.addParam("adName", apelido);
                        httpRequest.addParam("txtCep", cep);
                        httpRequest.addParam("address", rua);
                        httpRequest.addParam("numberAdr", num);
                        httpRequest.addParam("nbh", bairro);
                        httpRequest.addParam("city", cidade);
                        httpRequest.addParam("state", est);
                        httpRequest.addParam("compl", complemento);
                        httpRequest.addParam("id_usr", iduserLog);

                        try {
                            final InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();



                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if (success == 1){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastrarEnderecoActivity.this, "Novo endereço registrado com sucesso", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                            }
                            else{
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastrarEnderecoActivity.this, error, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}