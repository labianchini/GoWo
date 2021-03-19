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
import com.example.gowo.activity.ViewServicoActivity;
import com.example.gowo.model.Servico;
import com.example.gowo.model.Usuario;

import java.util.List;

public class MyAdapterPrest extends RecyclerView.Adapter{

    Context context;
    List<Servico> servicos;

    public MyAdapterPrest(Context context, List<Servico> servicos) {
        this.context = context;
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

        /*mageView imgUsu = holder.itemView.findViewById(R.id.imgViewEmpr);
        imgUsu.setImageBitmap(servico.getPhotoServ());*/

        TextView nomeServ = holder.itemView.findViewById(R.id.tvNomeServ);
        nomeServ.setText(servico.getNameServ());

        TextView valorServ = holder.itemView.findViewById(R.id.tvPreco);
        valorServ.setText(servico.getValorServ());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewServicoActivity.class);
                //i.putExtra("id", usuario.getIdUsu());
                //i.putExtra("categoria",usuario.getCategoria());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicos.size();
    }
}
