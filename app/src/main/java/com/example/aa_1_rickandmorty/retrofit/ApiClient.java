package com.example.aa_1_rickandmorty.retrofit;

import android.content.Context;

import com.example.aa_1_rickandmorty.beans.Container;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit retrofit;
    private Context context;

    private static final String BASE_URL = "https://rickandmortyapi.com/api/";

    public ApiClient(Context context){
        this.context = context;

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Call<Container> getPersons(Integer page){
        PersonApiInterface service = retrofit.create(PersonApiInterface.class);
        return service.getPersons(page);
    }
    public Call<Container> getPersonByName(Integer page, String name){
        PersonApiInterface service = retrofit.create(PersonApiInterface.class);
        return service.getPersonsByName(page, name);
    }

}
