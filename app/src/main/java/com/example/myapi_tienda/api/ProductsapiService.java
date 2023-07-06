package com.example.myapi_tienda.api;

import com.example.myapi_tienda.Products;
import com.example.myapi_tienda.ProductsRespuesta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsapiService {

@GET("products")
    Call<List<Products>> obtenerListaProducts();
}


