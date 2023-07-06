package com.example.myapi_tienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapi_tienda.api.ProductsapiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListaProductsAdapter listaProductsAdapter;
    Retrofit retrofit;
    ImageView imageView;
    private static final String TAG = "PRODUCTS";
    //private ListaProductsAdapter  listaProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.card_recycler_view);
        listaProductsAdapter = new ListaProductsAdapter(this);
        recyclerView.setAdapter(listaProductsAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imageView = findViewById(R.id.imagenGlide);
        setImageView();
        obtenerDatos();


    }


    private void setImageView() {

        String url = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg";
        Glide.with(this)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(imageView);

    }

    private void obtenerDatos() {
        ProductsapiService service = retrofit.create(ProductsapiService.class);
        Call<List<Products>> productsRespuestaCall = service.obtenerListaProducts();
        productsRespuestaCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.isSuccessful()) {
                    List<Products> productos = response.body();
                    for (int i = 0; i < productos.size(); i++) {
                        Products p= productos.get(i);
                        Log.e(TAG, "products: " + p.getTitle());
                    }

                   listaProductsAdapter.add((ArrayList<Products>) productos);
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }


        });

    }
}