package com.example.gowo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    List<Servico> servicos;

    public MyAdapter(Context context, List<Servico> servicos) {
        this.context = context;
        this.servicos = servicos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.feed_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Servico servico = this.servicos.get(position);

        ImageView imgViewEmpr = holder.itemView.findViewById(R.id.imgViewEmpr);
        imgViewEmpr.setImageBitmap(servico.getPhoto());

        TextView nomeEmpr = holder.itemView.findViewById(R.id.nomeEmpr);
        nomeEmpr.setText(servico.getName());

        TextView tvdescription = holder.itemView.findViewById(R.id.tvDescription);
        tvdescription.setText(servico.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quando clicar no servico

                Intent i = new Intent(context, ServicoActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.servicos.size();
    }
}

