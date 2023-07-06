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

public class ListaProductsAdapter extends  RecyclerView.Adapter<ListaProductsAdapter.ViewHolder>{

    private RecyclerView recyclerView;
    private ListaProductsAdapter listaProductsAdapter;
    private ArrayList<Products> dataset;
    private Context context;

    public class ViewHolder extends  RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.title);
            image=itemView.findViewById(R.id.image);

        }

    }
    public ListaProductsAdapter (Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ListaProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ListaProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products p=dataset.get(position);
        holder.title.setText(p.getTitle());

        String url ="";

        Glide.with(context)
                .load(p.getImage()

                )
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(holder.image);


    }

    @Override
    public int getItemCount() { return dataset.size();}

    public void add(ArrayList<Products> listaProducts) {
        dataset.addAll(listaProducts);
        notifyDataSetChanged();
    }
}

