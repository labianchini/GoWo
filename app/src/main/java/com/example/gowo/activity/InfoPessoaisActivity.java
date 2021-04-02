package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gowo.R;
import com.example.gowo.model.Usuario;
import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InfoPessoaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pessoais);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        final String email = i.getStringExtra("emailUser");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_return_user_data_from_email.php", "GET", "UTF-8");
                httpRequest.addParam("email", email);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        final String idUsu = jsonObject.getString("idUser");
                        final String nomeUsu = jsonObject.getString("usrName");
                        final String sobrenomeUsu = jsonObject.getString("usrLastName");
                        final String emailUsu = jsonObject.getString("usrEmail");
                        final String dataNUsu = jsonObject.getString("usrDateN");
                        final String telefoneUsu = jsonObject.getString("usrCellPhone");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /*ImageView imgFoto = findViewById(R.id.imgFoto);
                                imgFoto.setImageBitmap();*/

                                TextView txtNome = findViewById(R.id.txtNome);
                                txtNome.setText(nomeUsu);

                                TextView txtSobrenome = findViewById(R.id.txtSobrenome);
                                txtSobrenome.setText(sobrenomeUsu);

                                TextView txtEmail = findViewById(R.id.txtEmail);
                                txtEmail.setText(emailUsu);

                                TextView txtDataNasc = findViewById(R.id.txtDataNasc);
                                txtDataNasc.setText(dataNUsu);

                                TextView txtCelular = findViewById(R.id.txtCelular);
                                txtCelular.setText(telefoneUsu);
                            }
                        });
                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(InfoPessoaisActivity.this, error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}