package com.example.aa_1_rickandmorty.person.searchPerson.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;

import com.example.aa_1_rickandmorty.beans.Container;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.searchPerson.contract.SearchPersonContract;
import com.example.aa_1_rickandmorty.retrofit.ApiClient;
import com.example.aa_1_rickandmorty.utils.Post;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPersonModel implements SearchPersonContract.Model {

    private static final String basicURL = "https://rickandmortyapi.com/api/character";
    private String URL = "";
    private Container container = new Container();
    private Person personsaux;
    private ArrayList<Person> persons = new ArrayList<>();
    OnSearchPersonListener onSearchPersonListener;

    @Override
    public void getPersonsWS(Map conectdata, OnSearchPersonListener onSearchPersonListener) {

        ApiClient apiClient = new ApiClient((Context)conectdata.get("context"));
        Call<Container> request = apiClient.getPersonByName((Integer) conectdata.get("page"), (String) conectdata.get("name"));
        request.enqueue(new Callback<Container>() {
            @Override
            public void onResponse(@Nullable Call<Container> call, @Nullable Response<Container> response) {
                if(response == null || response.body() == null || response.body().getResults() == null || response.body().getResults().isEmpty()){
                    onSearchPersonListener.onFailure("Fin de la lista");
                }else{
                    onSearchPersonListener.onFinished(response.body().getResults());
                }
            }

            @Override
            public void onFailure(@Nullable Call<Container> call, @Nullable Throwable throwable) {
                onSearchPersonListener.onFailure(throwable.getMessage());
            }
        });
    }
}
