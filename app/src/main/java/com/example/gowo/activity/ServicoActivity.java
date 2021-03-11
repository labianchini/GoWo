package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gowo.R;
import com.example.gowo.model.Servico;
import com.example.gowo.model.ViewServicoViewModel;

public class ServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        ViewServicoViewModel viewServicoViewModel = new ViewModelProvider(this, new ViewServicoViewModel.ViewServicoViewModelFactory(id)).get(ViewServicoViewModel.class);

        LiveData<Servico> servico = viewServicoViewModel.getServico();
        servico.observe(this, new Observer<Servico>() {
            @Override
            public void onChanged(Servico servico) {
                ImageView imgViewEmpr = findViewById(R.id.imgViewEmpr);
                imgViewEmpr.setImageBitmap(servico.getPhotoServ());

                TextView txtViewNomeEmpr = findViewById(R.id.txtViewNomeEmpr);
                txtViewNomeEmpr.setText(servico.getNameServ());

                //TextView tvNamePrest = findViewById(R.id.tvNamePrest);
                //tvNamePrest.setText(servico.getIdPrest());

                TextView tvDrescServ = findViewById(R.id.tvDrescServ);
                tvDrescServ.setText(servico.getDescriptionServ());


            }
        });

        ImageButton imgBtnChat = findViewById(R.id.imgBtnChat);
        imgBtnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServicoActivity.this, ChatPrivActivity.class);
                startActivity(i);
            }
        });
    }
}