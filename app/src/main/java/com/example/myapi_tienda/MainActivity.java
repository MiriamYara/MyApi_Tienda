package com.example.myapi_tienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapi_tienda.api.TiendapiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListaTiendaAdapter listaTiendaAdapter;
    Retrofit retrofit;
    ImageView imageView;
    private static final String TAG = "TIENDA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.card_recycler_view);
        listaTiendaAdapter = new ListaTiendaAdapter(this);
        recyclerView.setAdapter(listaTiendaAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/products/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imageView = findViewById(R.id.imagenGlide);
        setImageView();
        obtenerDatos();


    }
    private void obtenerDatos2() {
        TiendapiService service = retrofit.create(TiendapiService.class);
        Call<TiendaRespuesta> tiendaRespuestaCall = service.obtenerListaTienda();
        tiendaRespuestaCall.enqueue(new Callback<TiendaRespuesta>() {
            @Override
            public void onResponse(Call<TiendaRespuesta> call, Response<TiendaRespuesta> response) {
                if (response.isSuccessful()) {
                    TiendaRespuesta tiendaRespuesta = response.body();
                    List<Tienda> listaTienda = tiendaRespuesta.getResults();

                    System.out.println(listaTienda);

                    for (int i = 0; i < listaTienda.size(); i++) {
                        Tienda t = listaTienda.get(i);
                        Log.e(TAG, " tienda: " + t.getTitle());
                        Log.e(TAG, " tienda: " + t.getImage());

                    }
                    listaTiendaAdapter.add((ArrayList<Tienda>) listaTienda);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<TiendaRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());

            }
        });

    }

    private void setImageView() {

        String url = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg";
        Glide.with(this)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(imageView);

    }

    private void obtenerDatos() {
        TiendapiService service = retrofit.create(TiendapiService.class);
        Call<TiendaRespuesta> tiendaRespuestaCall = service.obtenerListaTienda();
        tiendaRespuestaCall.enqueue(new Callback<TiendaRespuesta>() {
            @Override
            public void onResponse(Call<TiendaRespuesta> call, Response<TiendaRespuesta> response) {
                if (response.isSuccessful()) {
                    TiendaRespuesta tiendaRespuesta = response.body();
                    List<Tienda> listatienda = tiendaRespuesta.getResults();
                    for (int i = 0; i < listatienda.size(); i++) {
                        Tienda t = listatienda.get(i);
                        Log.e(TAG, "products: " + t.getTitle());
                    }

                    listaTiendaAdapter.add((ArrayList<Tienda>) listatienda);
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<TiendaRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

    }
}