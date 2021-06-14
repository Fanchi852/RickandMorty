package com.example.aa_1_rickandmorty.person.searchPerson.contract;

import com.example.aa_1_rickandmorty.beans.Person;

import java.util.ArrayList;

public interface SearchPersonContract {

    interface View{
        void success(ArrayList<Person> listPerson);
        void error(String error);
    }

    interface Presenter{
        void getPersons(Person person);
    }

    interface Model{
        void getPersonsWS(SearchPersonContract.Model.OnSearchPersonListener onSearchPersonListener,Person person);
        interface OnSearchPersonListener {
            void onFinished(ArrayList<Person> persons);
            void onFailure(String error);
        }
    }

}
