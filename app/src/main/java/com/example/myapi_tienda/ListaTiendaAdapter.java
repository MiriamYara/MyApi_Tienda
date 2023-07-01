package com.example.myapi_tienda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListaTiendaAdapter extends  RecyclerView.Adapter<ListaTiendaAdapter.ViewHolder>{

    private RecyclerView recyclerView;
    private ListaTiendaAdapter listaTiendaAdapter;
    private ArrayList<Tienda> dataset;
    private Context context;



    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView fotoImagenView;
        private TextView titulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             titulo= itemView.findViewById(R.id.titulo);
            fotoImagenView=itemView.findViewById(R.id.fotoImageView);
        }
    }

    public ListaTiendaAdapter(Context context){
        this.context = context;
        dataset=new ArrayList<>();
    }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
            return new ViewHolder(view);
        }
    @Override
    public int getItemCount() {
        return  dataset.size();
    }



        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        Tienda t = dataset.get(position);
        holder.titulo.setText(t.getTitle());


        String url ="https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg";

            //String url_foto = "https://fakestoreapi.com/img/";
            String url_foto = t.getImage();
            Glide.with(context)
                    //.load("url_foto"+t.getId()+".jpg")
                    .load(url_foto)

                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.fotoImagenView);

        }
    public void add(ArrayList<Tienda> listaTienda) {
        dataset.addAll(listaTienda);
        notifyDataSetChanged();
    }
    }