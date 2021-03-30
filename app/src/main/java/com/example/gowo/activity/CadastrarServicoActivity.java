package com.example.gowo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gowo.R;
import com.example.gowo.model.CadastrarServicoViewModel;
import com.example.gowo.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastrarServicoActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CadastrarServicoViewModel cadastrarServicoViewModel = new ViewModelProvider(this).get(CadastrarServicoViewModel.class);
        String selectPhotoLocation = cadastrarServicoViewModel.getSelectPhotoLocation();
        if (selectPhotoLocation != null){
            ImageButton imgBtn = findViewById(R.id.imgBtnAddFoto);
            int w = (int) getResources().getDimension(R.dimen.photoPreviewWidth);
            int h = (int) getResources().getDimension(R.dimen.photoPreviewHeight);
            Bitmap btm = Util.getBitmap(selectPhotoLocation, w, h);
            imgBtn.setImageBitmap(btm);
        }

        ImageButton imgBtn = findViewById(R.id.imgBtnAddPhoto);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        Button btnAddUsu = findViewById(R.id.btnAddServ);
        btnAddUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String selectPhotoLocation = cadastrarServicoViewModel.getSelectPhotoLocation();
                if (selectPhotoLocation == null){
                    Toast.makeText(CadastrarServicoActivity.this, "Campo de foto não preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                // spinner categoria
                final Spinner spinnerCategoria = findViewById(R.id.spnrCategoria);
                spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String categoria = spinnerCategoria.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(CadastrarServicoActivity.this, "Campo de categoria não preenchido", Toast.LENGTH_LONG).show();
                        return;
                    }
                });

                EditText valorServ =  findViewById(R.id.valorServ);
                final String valor = valorServ.getText().toString();
                if(valor.isEmpty()) {
                    Toast.makeText(CadastrarServicoActivity.this, "Campo de valor não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText descrServ =  findViewById(R.id.descrServ);
                final String descricao = descrServ.getText().toString();
                if(descricao.isEmpty()) {
                    Toast.makeText(CadastrarServicoActivity.this, "Campo de descrição não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText cidadeServ =  findViewById(R.id.cidadeServ);
                final String cidade = cidadeServ.getText().toString();
                if(cidade.isEmpty()) {
                    Toast.makeText(CadastrarServicoActivity.this, "Campo de cidade não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText bairroServ =  findViewById(R.id.bairroServ);
                final String bairro = bairroServ.getText().toString();
                if(bairro.isEmpty()) {
                    Toast.makeText(CadastrarServicoActivity.this, "Campo de bairro não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
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
            if(resultCode == Activity.RESULT_OK){
                Uri selectPhotoLocation = data.getData();
                Bitmap bmp = null;
                try {
                    bmp = Util.getBitmap(this, selectPhotoLocation, 4);
                    File f = createImageFile();
                    Util.saveBitmap(f.getAbsolutePath(), bmp);
                    ImageButton imgBtn = findViewById(R.id.imgBtnAddPhoto);
                    imgBtn.setImageBitmap(bmp);

                    CadastrarServicoViewModel cadastrarServicoViewModel = new ViewModelProvider(this).get(CadastrarServicoViewModel.class);
                    cadastrarServicoViewModel.setSelectPhotoLocation(f.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}