package com.example.aa_1_rickandmorty.person.listPerson.contract;

import com.example.aa_1_rickandmorty.beans.Person;

import java.util.ArrayList;

public interface ListPersonContract {

    interface View{
        void success(ArrayList<Person> listPerson);
        void error(String error);
    }

    interface Presenter{
        void getPersons();
    }

    interface Model{
        void getPersonsWS(OnListPersonListener onListPersonListener);
        interface OnListPersonListener {
            void onFinished(ArrayList<Person> persons);
            void onFailure(String Error);
        }
    }

}
