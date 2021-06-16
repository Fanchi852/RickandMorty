package com.example.aa_1_rickandmorty.person.searchPerson.contract;

import com.example.aa_1_rickandmorty.beans.Person;

import java.util.ArrayList;
import java.util.Map;

public interface SearchPersonContract {

    interface View{
        void success(ArrayList<Person> listPerson);
        void error(String error);
    }

    interface Presenter{
        void getPersons(Map conectdata);
    }

    interface Model{
        void getPersonsWS(Map conectdata, SearchPersonContract.Model.OnSearchPersonListener onSearchPersonListener);
        interface OnSearchPersonListener {
            void onFinished(ArrayList<Person> persons);
            void onFailure(String error);
        }
    }

}
