package com.example.aa_1_rickandmorty.person.searchPerson.model;

import android.os.AsyncTask;

import com.example.aa_1_rickandmorty.beans.Container;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.searchPerson.contract.SearchPersonContract;
import com.example.aa_1_rickandmorty.utils.Post;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchPersonModel implements SearchPersonContract.Model {

    private static final String basicURL = "https://rickandmortyapi.com/api/character";
    private String URL = "";
    private Container container = new Container();
    private Person personsaux;
    private ArrayList<Person> persons = new ArrayList<>();
    OnSearchPersonListener onSearchPersonListener;

    @Override
    public void getPersonsWS(OnSearchPersonListener onSearchPersonListener, Person person) {

        container.getInfo().setNext("vacio");
        this.onSearchPersonListener = onSearchPersonListener;
        SearchPersonModel.PersonAsynTask pt = new SearchPersonModel.PersonAsynTask();
        pt.execute();
        personsaux = person;

    }

    class PersonAsynTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean res = false;
            Post post = new Post();
            try {
                URL = basicURL+"?name="+personsaux.getName();
                JSONObject jscontainer = post.getServerDataGetObject(URL);
                Gson gson = new Gson();
                container = gson.fromJson(jscontainer.toString(), Container.class);
                persons.clear();
                persons.addAll(container.getResults());

                if (container.getResults() != null && persons.size() > 0) {
                    res = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(Boolean res) {

            if(res){
                onSearchPersonListener.onFinished(persons);
            }else{
                onSearchPersonListener.onFailure("La busqueda no ha podido realizarse");
            }

        }
    }

}
