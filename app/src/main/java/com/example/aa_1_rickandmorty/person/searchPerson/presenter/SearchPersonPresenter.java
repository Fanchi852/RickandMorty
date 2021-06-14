package com.example.aa_1_rickandmorty.person.searchPerson.presenter;

import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.searchPerson.contract.SearchPersonContract;
import com.example.aa_1_rickandmorty.person.searchPerson.model.SearchPersonModel;

import java.util.ArrayList;

public class SearchPersonPresenter implements SearchPersonContract.Presenter {

    private SearchPersonContract.View view;
    private SearchPersonModel searchPersonModel;

    public SearchPersonPresenter(SearchPersonContract.View view){
        this.view = view;
        this.searchPersonModel = new SearchPersonModel();
    }


    @Override
    public void getPersons(Person person) {
        searchPersonModel.getPersonsWS(new SearchPersonContract.Model.OnSearchPersonListener() {
            @Override
            public void onFinished(ArrayList<Person> persons) {
                view.success(persons);
            }

            @Override
            public void onFailure(String error) {
                view.error("ERROR: "+error);
            }
        }, person);
    }
}
