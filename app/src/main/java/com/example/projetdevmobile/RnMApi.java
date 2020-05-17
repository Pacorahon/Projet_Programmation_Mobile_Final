package com.example.projetdevmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RnMApi {
    @GET("api/character")
    Call<RestRnMResponse> getRnMResponse();
}
