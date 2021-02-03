package com.example.gowo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton imgBtnFacebook = findViewById(R.id.imgBtnFacebook);
        imgBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        ImageButton imgBtnGoogle = findViewById(R.id.imgBtnGoogle);
        imgBtnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        Button BtnEntrar = findViewById(R.id.btnEntrar);
        BtnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        Button BtnCliqueAqui = findViewById(R.id.btnCliqueAqui);
        BtnCliqueAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CadastrarUsuarioActivity.class);
                startActivity(i);
            }
        });
    }
}