package com.example.gowo.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
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

        ViewServicoViewModel viewServicoViewModel = new ViewModelProvider(this, new ViewServicoViewModel.ViewServicoViewModelFactory(id)).get(ViewServicoViewModel.class);

        LiveData<Servico> servico = viewServicoViewModel.getServico();
        servico.observe(this, new Observer<Servico>() {
            @Override
            public void onChanged(Servico servico) {
                ImageView imgServ = findViewById(R.id.imgServ);
                imgServ.setImageBitmap(servico.getPhotoServ());

                TextView txtViewNomeServ = findViewById(R.id.txtViewNomeServ);
                txtViewNomeServ.setText(servico.getNameServ());

                TextView txtViewEndereco = findViewById(R.id.txtViewEndereco);
                txtViewEndereco.setText(servico.getEndereco());

                TextView textViewCateg = findViewById(R.id.textViewCateg);
                textViewCateg.setText(servico.getCategoria());

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