package com.example.aa_1_rickandmorty.person.listPerson.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;

import com.example.aa_1_rickandmorty.beans.Container;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.listPerson.contract.ListPersonContract;
import com.example.aa_1_rickandmorty.retrofit.ApiClient;
import com.example.aa_1_rickandmorty.utils.Post;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListPersonModel implements ListPersonContract.Model {

    private static final String basicURL = "https://rickandmortyapi.com/api/character";
    private String URL = "";
    private Container container = new Container();
    private ArrayList<Person> persons = new ArrayList<>();
    OnListPersonListener onListPersonListener;

    @Override
    public void getPersonsWS(Map conectdata, OnListPersonListener onListPersonListener) {

        ApiClient apiClient = new ApiClient((Context)conectdata.get("context"));
        Call<Container> request = apiClient.getPersons((Integer)conectdata.get("page"));
        request.enqueue(new Callback<Container>() {
            @Override
            public void onResponse(@Nullable Call<Container> call, @Nullable Response<Container> response) {
                onListPersonListener.onFinished(response.body().getResults());
            }

            @Override
            public void onFailure(@Nullable Call<Container> call, @Nullable Throwable throwable) {
                onListPersonListener.onFailure(throwable.getMessage());
            }
        });
    }
}
