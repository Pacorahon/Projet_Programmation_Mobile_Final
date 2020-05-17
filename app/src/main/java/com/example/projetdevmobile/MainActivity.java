package com.example.projetdevmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Character> characterList =getDataFromCache();
        if(characterList != null){
            ShowList(characterList);
        } else {
            makeApiCall();
        }
    }

    private List<Character> getDataFromCache() {
        String JsonCharacter = sharedPreferences.getString("JsonCharacterList",null);
        if(JsonCharacter == null) {
            return null;
        } else {
            Type ListType = new TypeToken<List<Character>>(){}.getType();
            return gson.fromJson(JsonCharacter,ListType);
        }
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RnMApi RnMApi = retrofit.create(RnMApi.class);

        Call<List<Character>> call =RnMApi.getRnMResponse();
        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if(response.isSuccessful()){
                    List<Character> characterList = response.body();
                    saveList(characterList);
                    ShowList(characterList);
                } else {
                    showError();
                }

            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                showError();
            }
        });


    }

    private void saveList(List<Character> characterList) {
        String JsonString = gson.toJson(characterList);
        sharedPreferences
                .edit()
                .putString("JsonCharacterList",JsonString)
                .apply();
        Toast.makeText(getApplicationContext(),"Liste sauvegardée",Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(),"API ERROR",Toast.LENGTH_SHORT).show();
    }

}
