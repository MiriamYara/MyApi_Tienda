package com.example.myapi_tienda.api;

import com.example.myapi_tienda.TiendaRespuesta;
import com.example.myapi_tienda.TiendaRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TiendapiService {

@GET("products")
    Call<TiendaRespuesta> obtenerListaTienda();
}
