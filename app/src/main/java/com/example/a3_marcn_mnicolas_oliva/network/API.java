package com.example.a3_marcn_mnicolas_oliva.network;

import com.example.a3_marcn_mnicolas_oliva.models.ItemResponse;
import com.example.a3_marcn_mnicolas_oliva.models.Zelda;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    // Base URL
    public final String BASE_URL = "https://botw-compendium.herokuapp.com/api/v2/category/";
    // Connection endpoints
    // https://botw-compendium.herokuapp.com/api/v2/category/equipment
    @GET("equipment")
    public Call<ItemResponse> getAllItems();

}
