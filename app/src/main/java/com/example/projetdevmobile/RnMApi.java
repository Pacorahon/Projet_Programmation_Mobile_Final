package com.example.projetdevmobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RnMApi {
    @GET("apiLOL.json")
    Call<List<Character>> getRnMResponse();
}
