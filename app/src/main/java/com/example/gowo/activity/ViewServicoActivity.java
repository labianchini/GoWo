package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gowo.R;
import com.example.gowo.model.Servico;
import com.example.gowo.model.ViewServicoViewModel;

public class ViewServicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_servico);

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
                ImageView imgServ = findViewById(R.id.imgServ);
                imgServ.setImageBitmap(servico.getPhotoServ());

                TextView txtViewNomeServ = findViewById(R.id.txtViewNomeServ);
                txtViewNomeServ.setText(servico.getNameServ());

                /*TextView txtViewNomePrest = findViewById(R.id.txtViewNomePrest);
                txtViewNomePrest.setText(servico.);*/

                /*TextView txtViewBairro = findViewById(R.id.txtViewBairro);
                txtViewBairro.setText(servico.);*/

                /*TextView txtViewCidade = findViewById(R.id.txtViewCidade);
                txtViewCidade.setText(servico.);*/

                TextView txtViewDescr = findViewById(R.id.txtViewDescr);
                txtViewDescr.setText(servico.getDescriptionServ());

                if (servico.getValorServ()=="null"){
                    TextView txtViewValor = findViewById(R.id.txtViewValor);
                    txtViewValor.setText("Valor não definido");
                }
                else {
                    TextView txtViewValor = findViewById(R.id.txtViewValor);
                    txtViewValor.setText(servico.getValorServ());
                }
            }
        });
    }
}