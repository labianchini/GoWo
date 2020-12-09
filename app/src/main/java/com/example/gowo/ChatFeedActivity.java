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

public class ChatFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_feed);

        Toolbar toolbarmain = findViewById(R.id.toolbarChatFeed);  // tornar a toolbar principal
        setSupportActionBar(toolbarmain);

        Button imgBtnTeste = findViewById(R.id.btnTeste);  // coloquei esse código só para eu conseguir mudar de página
        imgBtnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatFeedActivity.this, ChatPrivActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // para o menu aparecer na toolbar
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  // funcão quando apertar em cada item
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.home:
                Intent i = new Intent(ChatFeedActivity.this,HomeActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.chat:
                Intent i = new Intent(ChatFeedActivity.this, ChatFeedActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.perfil:
                Intent i = new Intent(ChatFeedActivity.this,PerfilActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
}