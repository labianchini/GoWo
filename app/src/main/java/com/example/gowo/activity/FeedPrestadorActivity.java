package com.example.gowo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gowo.R;
import com.example.gowo.adapter.MyAdapterPrest;
import com.example.gowo.model.FeedPrestadorViewModel;
import com.example.gowo.model.Servico;
import com.example.gowo.model.Usuario;

import java.util.List;

public class FeedPrestadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_prestador);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String categoria = i.getStringExtra("categoria");
        final String telefone = i.getStringExtra("telefone");
        final String email = i.getStringExtra("email");

        ImageButton imgBtnWhats = findViewById(R.id.imgBtnWhats);
        imgBtnWhats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone="+telefone;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        ImageButton imgBtnEmail = findViewById(R.id.imgBtnEmail);
        imgBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
                startActivity(i);
            }
        });

        FeedPrestadorViewModel feedPrestadorViewModel = new ViewModelProvider(this, new FeedPrestadorViewModel.FeedPrestadorViewModelFactory(id, categoria)).get(FeedPrestadorViewModel.class);

        final RecyclerView rvServUsu = findViewById(R.id.rvServUsu);
        rvServUsu.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvServUsu.setLayoutManager(layoutManager);

        final LiveData<Usuario> usuario = feedPrestadorViewModel.getUsuario(); // Livedata- cria uma lista com os servicos que pode ser observada, mas não alterada
        usuario.observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario u) {
                TextView txtViewNomeEmpr = findViewById(R.id.txtViewNomeEmpr);
                txtViewNomeEmpr.setText(u.getNameUsu()+ " " + u.getSobrenomeUsu());

                ImageView imgViewEmpr = findViewById(R.id.imgViewEmpr);
                imgViewEmpr.setImageBitmap(u.getImgUsu());
            }
        });

        final LiveData<List<Servico>> servicos = feedPrestadorViewModel.getServicos();
        servicos.observe(this, new Observer<List<Servico>>() {
            @Override
            public void onChanged(List<Servico> servicos) {
                MyAdapterPrest myAdapterPrest = new MyAdapterPrest(FeedPrestadorActivity.this, telefone, email, servicos); //A mainActivity é avisada que chegou uma nova lista
                rvServUsu.setAdapter(myAdapterPrest);  //A interface é atualizada
            }
        });
    }
}