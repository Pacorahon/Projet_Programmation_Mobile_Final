package com.example.projetdevmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://raw.githubusercontent.com/Pacorahon/Projet_Programmation_Mobile_Final/master/app/src/main/java/com/example/projetdevmobile/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShowList();
        makeApiCall();
    }

    private void ShowList(List<Character> characterList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(characterList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RnMApi RnMApi = retrofit.create(RnMApi.class);

        Call<RestRnMResponse> call =RnMApi.getRnMResponse();
        call.enqueue(new Callback<RestRnMResponse>() {
            @Override
            public void onResponse(Call<RestRnMResponse> call, Response<RestRnMResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Character> CharacterList = response.body().getResults();
                    ShowList(CharacterList);
                } else {
                    showError();
                }

            }

            @Override
            public void onFailure(Call<RestRnMResponse> call, Throwable t) {
                showError();
            }
        });


    }

    private void showError() {
        Toast.makeText(getApplicationContext(),"API ERROR",Toast.LENGTH_SHORT).show();
    }

}
