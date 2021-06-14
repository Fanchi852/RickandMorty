package com.example.aa_1_rickandmorty.person.searchPerson.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa_1_rickandmorty.R;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.adapter.AdapterPerson;
import com.example.aa_1_rickandmorty.person.searchPerson.contract.SearchPersonContract;
import com.example.aa_1_rickandmorty.person.searchPerson.presenter.SearchPersonPresenter;

import java.util.ArrayList;

public class SearchPersonActivity extends AppCompatActivity implements SearchPersonContract.View {

    private Button search_button;
    private TextView search_txt;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;

    private SearchPersonPresenter searchPersonPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();

        searchPersonPresenter = new SearchPersonPresenter(this);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Person person = new Person();
                person.setName(search_txt.getText().toString());
                System.out.println(search_txt.getText().toString());
                System.out.println(person.toStringList());
                searchPersonPresenter.getPersons(person);

            }
        });

    }

    @Override
    public void success(ArrayList<Person> listPerson) {
        AdapterPerson adapter = new AdapterPerson(listPerson, this);
        recyclerView.setLayoutManager(lManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error,Toast.LENGTH_SHORT).show();
    }

    private void initComponents(){

        search_button = findViewById(R.id.searchbutton);
        recyclerView = findViewById(R.id.recyclerSearchId);
        lManager = new LinearLayoutManager(this);
        search_txt = findViewById(R.id.nombresearchtxt);

    }
}
