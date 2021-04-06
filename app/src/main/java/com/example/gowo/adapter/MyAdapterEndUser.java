package com.example.gowo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowo.R;
import com.example.gowo.model.Endereco;

import java.util.List;

public class MyAdapterEndUser extends RecyclerView.Adapter {

    Context context;
    List<Endereco> enderecos;

    public MyAdapterEndUser(Context context, List<Endereco> enderecos) {
        this.context = context;
        this.enderecos = enderecos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.endereco_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Endereco endereco = this.enderecos.get(position);

        TextView tvApelido = holder.itemView.findViewById(R.id.tvApelido);
        tvApelido.setText(endereco.getApelido());

        String end = endereco.getRua() + ", " + endereco.getNumero() + " " + endereco.getComplemento() + " - " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getEstado() + ", " + endereco.getCep();

        TextView tvEndereco = holder.itemView.findViewById(R.id.tvEndereco);
        tvEndereco.setText(end);
    }

    @Override
    public int getItemCount() {return enderecos.size();}
}
