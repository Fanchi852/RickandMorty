package com.example.aa_1_rickandmorty.retrofit;

import com.example.aa_1_rickandmorty.beans.Container;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonApiInterface {

    @GET("character")
    Call<Container> getPersons(@Query("page") Integer page);

    @GET("character")
    Call<Container> getPersonsByName(@Query("page") Integer page,
                                     @Query("name") String string
                                     );

}
