package com.example.gowo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowo.R;
import com.example.gowo.activity.ViewServicoActivity;
import com.example.gowo.model.Servico;

import java.util.List;

public class MyAdapterPrest extends RecyclerView.Adapter{

    Context context;
    List<Servico> servicos;
    String telefone;
    String email;

    public MyAdapterPrest(Context context, String telefone, String email, List<Servico> servicos) {
        this.context = context;
        this.telefone = telefone;
        this.email = email;
        this.servicos = servicos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.servico_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Servico servico = this.servicos.get(position);

        if (servico.getPhotoServ()==null) {
            String oi= "oi";
        }
        else {
            ImageView imgUsu = holder.itemView.findViewById(R.id.imgServico);
            imgUsu.setImageBitmap(servico.getPhotoServ());
        }

        TextView nomeServ = holder.itemView.findViewById(R.id.tvNomeServ);
        nomeServ.setText(servico.getNameServ());

        if (servico.getValorServ()=="null"){
            TextView valorServ = holder.itemView.findViewById(R.id.tvPreco);
            valorServ.setText("Valor não definido");
        }
        else {
            TextView valorServ = holder.itemView.findViewById(R.id.tvPreco);
            valorServ.setText(servico.getValorServ());
        }

        TextView tvLocal = holder.itemView.findViewById(R.id.tvLocal);
        tvLocal.setText(servico.getEndereco());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewServicoActivity.class);
                i.putExtra("id", servico.getIdServ());
                i.putExtra("telefone", telefone);
                i.putExtra("email", email);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }
}
