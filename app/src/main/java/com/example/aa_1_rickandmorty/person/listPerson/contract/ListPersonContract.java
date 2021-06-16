package com.example.aa_1_rickandmorty.person.listPerson.contract;

import android.content.Context;

import com.example.aa_1_rickandmorty.beans.Person;

import java.util.ArrayList;
import java.util.Map;

public interface ListPersonContract {

    interface View{
        void success(ArrayList<Person> listPerson);
        void error(String error);
    }

    interface Presenter{
        void getPersons(Map conectdata);
    }

    interface Model{
        void getPersonsWS(Map conectdata,OnListPersonListener onListPersonListener);
        interface OnListPersonListener {
            void onFinished(ArrayList<Person> persons);
            void onFailure(String Error);
        }
    }

}
