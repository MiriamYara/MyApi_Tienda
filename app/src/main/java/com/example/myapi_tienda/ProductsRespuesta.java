package com.example.myapi_tienda;

import java.util.ArrayList;

public class ProductsRespuesta {
    private static ArrayList<Products> results = new ArrayList<Products>();

    public static ArrayList<Products> getResults() {
        return results;
    }

    public void setResults(ArrayList<Products> results) {
        this.results = results;
    }

}
