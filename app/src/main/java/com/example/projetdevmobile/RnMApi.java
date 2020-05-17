package com.example.projetdevmobile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RnMApi {
    @GET("api.json")
    Call<RestRnMResponse> getRnMResponse();
}
