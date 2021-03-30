package com.example.gowo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gowo.R;
import com.example.gowo.model.CadastrarUsuarioViewModel;
import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CadastrarUsuarioViewModel cadastrarUsuarioViewModel = new ViewModelProvider(this).get(CadastrarUsuarioViewModel.class);
        String selectPhotoLocation = cadastrarUsuarioViewModel.getSelectPhotoLocation();
        if (selectPhotoLocation != null){
            ImageButton imgBtn = findViewById(R.id.imgBtnAddFoto);
            int w = (int) getResources().getDimension(R.dimen.photoPreviewWidth);
            int h = (int) getResources().getDimension(R.dimen.photoPreviewHeight);
            Bitmap bmp = Util.getBitmap(selectPhotoLocation, w, h);
            imgBtn.setImageBitmap(bmp);
        }

        ImageButton imgBtn = findViewById(R.id.imgBtnAddFoto);
        imgBtn.setOnClickListener(new View.OnClickListener() {
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
                final String selectPhotoLocation = cadastrarUsuarioViewModel.getSelectPhotoLocation();
                if (selectPhotoLocation == null){
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de foto não preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

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
                EditText pswdUsu =  findViewById(R.id.pswdUsu);
                final String senha = pswdUsu.getText().toString();
                if(senha.isEmpty()) {
                    Toast.makeText(CadastrarUsuarioActivity.this, "Campo de senha não preenchido", Toast.LENGTH_LONG).show();
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
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_user_register.php", "POST", "UTF-8");
                        httpRequest.addParam("nameNewUser", nome);
                        httpRequest.addParam("lastNameNewUser", sobrenome);
                        httpRequest.addParam("newEmail", email);
                        httpRequest.addParam("pwd", senha);
                        httpRequest.addParam("dateBorn", dataNasc);
                        httpRequest.addParam("cell", telefone);
                        httpRequest.addFile("img", new File(selectPhotoLocation));
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

    private File createImageFile() throws IOException {  // função para criar a imagem
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());  // transformar a data em uma string
        String imageFileName = "JPEG" + timeStamp;  // nome do arquivo
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File f = File.createTempFile(imageFileName, ".jpg", storageDir);
        return f;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST){
            if (resultCode == Activity.RESULT_OK) {
                Uri selectPhotoLocation = data.getData();
                Bitmap bmp = null;
                try {
                    bmp = Util.getBitmap(this, selectPhotoLocation, 4);
                    File f = createImageFile();
                    Util.saveBitmap(f.getAbsolutePath(), bmp);
                    ImageButton imgBtn = findViewById(R.id.imgBtnAddFoto);
                    imgBtn.setImageBitmap(bmp);

                    CadastrarUsuarioViewModel cadastrarUsuarioViewModel = new ViewModelProvider(this).get(CadastrarUsuarioViewModel.class);
                    cadastrarUsuarioViewModel.setSelectPhotoLocation(f.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



