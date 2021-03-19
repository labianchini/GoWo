package com.example.gowo.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gowo.R;
import com.example.gowo.activity.FeedPrestadorActivity;
import com.example.gowo.model.Usuario;

import java.util.List;

public class MyAdapterFeed extends RecyclerView.Adapter {

    Context context;
    List<Usuario> usuarios;
    String categoria;

    public MyAdapterFeed(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    public MyAdapterFeed(Context context, String categoria) {
        this.context = context;
        this.categoria = categoria;
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
        final Usuario usuario = this.usuarios.get(position);

        ImageView imgUsu = holder.itemView.findViewById(R.id.imgViewEmpr);
        imgUsu.setImageBitmap(usuario.getImgUsu());

        TextView nomeUsu = holder.itemView.findViewById(R.id.nome);
        nomeUsu.setText(usuario.getNameUsu());

        TextView endCidade = holder.itemView.findViewById(R.id.endCidade);
        endCidade.setText(usuario.getEndCidade());

        TextView endBairro = holder.itemView.findViewById(R.id.endBairro);
        endBairro.setText(usuario.getEndBairro());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FeedPrestadorActivity.class);
                i.putExtra("id", usuario.getIdUsu());
                i.putExtra("categoria",categoria);
                Log.d("categoria", categoria);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}

