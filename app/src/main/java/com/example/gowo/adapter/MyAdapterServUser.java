package com.example.gowo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowo.R;
import com.example.gowo.model.Servico;

import java.util.List;

public class MyAdapterServUser extends RecyclerView.Adapter {

    Context context;
    List<Servico> servicos;

    public MyAdapterServUser(Context context, List<Servico> servicos) {
        this.context = context;
        this.servicos = servicos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.meu_servico_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Servico servico = this.servicos.get(position);

        if (servico.getPhotoServ()!=null) {
            ImageView imgServ = holder.itemView.findViewById(R.id.imgServ);
            imgServ.setImageBitmap(servico.getPhotoServ());
        }

        TextView tvNomeServ = holder.itemView.findViewById(R.id.tvNomeServ);
        tvNomeServ.setText(servico.getNameServ());

        TextView tvCategoria = holder.itemView.findViewById(R.id.tvCategoria);
        tvCategoria.setText(servico.getCategoria());

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

        TextView tvDesc = holder.itemView.findViewById(R.id.tvDesc);
        tvDesc.setText(servico.getDescriptionServ());
    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }
}
