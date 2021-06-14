package com.example.aa_1_rickandmorty.person.listPerson.model;

import android.os.AsyncTask;

import com.example.aa_1_rickandmorty.beans.Container;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.listPerson.contract.ListPersonContract;
import com.example.aa_1_rickandmorty.utils.Post;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;


public class ListPersonModel implements ListPersonContract.Model {

    private static final String basicURL = "https://rickandmortyapi.com/api/character";
    private String URL = "";
    private Container container = new Container();
    private ArrayList<Person> persons = new ArrayList<>();
    OnListPersonListener onListPersonListener;

    @Override
    public void getPersonsWS(OnListPersonListener onListPersonListener) {

        container.getInfo().setNext("vacio");
        this.onListPersonListener = onListPersonListener;
        PersonAsynTask pt = new PersonAsynTask();
        pt.execute();
    }

    class PersonAsynTask extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean res = false;
            Post post = new Post();

            try {
                Integer num = 1;
                while (container.getInfo().getNext() != null) {
                    URL = basicURL+"?page="+num;
                    num ++;
                    JSONObject jscontainer = post.getServerDataGetObject(URL);
                    Gson gson = new Gson();
                    container = gson.fromJson(jscontainer.toString(), Container.class);
                    persons.addAll(container.getResults());
                }
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
                onListPersonListener.onFinished(persons);
            }else{
                onListPersonListener.onFailure("Error en la carga de los archivos");
            }

        }
    }
}
