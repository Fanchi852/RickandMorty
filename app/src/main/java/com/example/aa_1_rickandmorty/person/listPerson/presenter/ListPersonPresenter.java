package com.example.aa_1_rickandmorty.person.listPerson.presenter;

import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.listPerson.contract.ListPersonContract;
import com.example.aa_1_rickandmorty.person.listPerson.model.ListPersonModel;

import java.util.ArrayList;

public class ListPersonPresenter implements ListPersonContract.Presenter {

    private ListPersonContract.View view;
    private ListPersonModel listPersonModel;

    public ListPersonPresenter(ListPersonContract.View view) {
        this.view = view;
        this.listPersonModel = new ListPersonModel();
    }

    @Override
    public void getPersons() {

        listPersonModel.getPersonsWS(new ListPersonContract.Model.OnListPersonListener() {
            @Override
            public void onFinished(ArrayList<Person> persons) {
                view.success(persons);
            }

            @Override
            public void onFailure(String error) {
                view.error("ERROR: " + error);
            }
        });

    }
}
