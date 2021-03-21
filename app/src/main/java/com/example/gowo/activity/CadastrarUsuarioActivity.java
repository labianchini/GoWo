package com.example.gowo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gowo.R;
import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    Uri selectPhotoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageButton imgBtnAddFoto = findViewById(R.id.imgBtnAddFoto);
        imgBtnAddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        Button btnAddUsu = findViewById(R.id.btnAddUsu);
        btnAddUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ImageView imgPhotoUsu = findViewById(R.id.imgPhotoUsu);

                EditText nomeUsu =  findViewById(R.id.nomeUsu);
                final String nome = nomeUsu.getText().toString();
                if (nome.isEmpty()){
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de nome não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText sobrenomeUsu =  findViewById(R.id.sobrenomeUsu);
                final String sobrenome = sobrenomeUsu.getText().toString();
                if(sobrenome.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de sobrenome não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText emailUsu =  findViewById(R.id.emailUsu);
                final String email = emailUsu.getText().toString();
                if(email.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de email não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText pswdUsu1 =  findViewById(R.id.pswdUsu);
                final String senha1 = pswdUsu1.getText().toString();
                if(senha1.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText pswdUsu2 =  findViewById(R.id.pswdUsu2);
                final String senha2 = pswdUsu2.getText().toString();
                if(senha2.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de confirme sua senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!senha1.equals(senha2)) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Senha não confere", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText dataUsu =  findViewById(R.id.dataUsu);
                final String dataNasc = dataUsu.getText().toString();
                if(dataNasc.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de data de nascimento não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText telUsu =  findViewById(R.id.telUsu);
                final String telefone = telUsu.getText().toString();
                if(telefone.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de telefone não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText cepUsu =  findViewById(R.id.cepUsu);
                final String cep = cepUsu.getText().toString();
                if(cep.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de cep não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText estadoUsu =  findViewById(R.id.estadoUsu);
                final String estado = estadoUsu.getText().toString();
                if(estado.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de estado não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText cidadeUsu =  findViewById(R.id.cidadeUsu);
                final String cidade = cidadeUsu.getText().toString();
                if(cidade.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de cidade não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText bairroUsu =  findViewById(R.id.bairroUsu);
                final String bairro = bairroUsu.getText().toString();
                if(bairro.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de bairro não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText ruaUsu =  findViewById(R.id.ruaUsu);
                final String rua = ruaUsu.getText().toString();
                if(rua.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de rua não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText numUsu =  findViewById(R.id.numUsu);
                final String numero = numUsu.getText().toString();
                if(numero.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de numero não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/register.php", "POST", "UTF-8");
                        httpRequest.addParam("nome", nome);
                        httpRequest.addParam("last_name", sobrenome);
                        httpRequest.addParam("email", email);
                        //httpRequest.addParam("senha1", senha1);
                        //httpRequest.addParam("senha2", senha2);
                        httpRequest.addParam("dateN", dataNasc);
                        httpRequest.addParam("cell", telefone);
                        //httpRequest.addParam("profile_photo", foto);
                        //httpRequest.addParam("cep", cep);
                        //httpRequest.addParam("estado", estado);
                        //httpRequest.addParam("cidade", cidade);
                        //httpRequest.addParam("bairro", bairro);
                        //httpRequest.addParam("rua", rua);
                        //httpRequest.addParam("numero", numero);

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Util.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if(success == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastrarUsuarioActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                            }
                            else {
                                final String error = jsonObject.getString("error");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastrarUsuarioActivity.this, error, Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                selectPhotoLocation = data.getData();
                ImageView imvPhotoPreview = findViewById(R.id.imgPhotoUsu);
                imvPhotoPreview.setImageURI(selectPhotoLocation);
            }
        }
    }
}



