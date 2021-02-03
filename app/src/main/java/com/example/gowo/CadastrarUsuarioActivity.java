package com.example.gowo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    //static int RESULT_REQUEST_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*ImageButton imgBtnAddFoto = findViewById(R.id.imgBtnAddFoto);
        imgBtnAddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> permissions = new ArrayList<>(); //É criado uma lista de permissões
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE); //E a permissão de inserir o arquivo extenamente
                checkForPermissions(permissions); //É checado se o usuário concedeu todas as permissões pedidas


            }
        });*/
    }

    /*private void checkForPermissions(List<String> permissions){ //Função para checar se todas as permissões foram concedidas
        List<String> permissionsNotGranted = new ArrayList<>();

        for(String permission : permissions){
            if( !hasPermission(permission)) {
                permissionsNotGranted.add(permission);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (permissionsNotGranted.size() > 0){
                requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]),RESULT_REQUEST_PERMISSION);
            }
        }
    }
    private boolean hasPermission(String permission){ //Função que retorna true ou false para as concessões de permissão
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            return ActivityCompat.checkSelfPermission(CadastrarUsuarioActivity.this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }*/

}