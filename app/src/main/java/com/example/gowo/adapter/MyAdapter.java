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
import com.example.gowo.activity.FeedPrestadorActivity;
import com.example.gowo.model.Usuario;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    List<Usuario> usuarios;

    public MyAdapter(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
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

        ImageView imgViewEmpr = holder.itemView.findViewById(R.id.imgViewEmpr);
        imgViewEmpr.setImageBitmap(usuario.getImgUsu());

        TextView nome = holder.itemView.findViewById(R.id.nome);
        nome.setText(usuario.getNameUsu());

        TextView sobrenome = holder.itemView.findViewById(R.id.sobrenome);
        sobrenome.setText(usuario.getSobrenomeUsu());

        TextView endereco = holder.itemView.findViewById(R.id.endereco);
        endereco.setText(usuario.getEnderecoUsu());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FeedPrestadorActivity.class);
                i.putExtra("id", usuario.getIdUsu());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}

