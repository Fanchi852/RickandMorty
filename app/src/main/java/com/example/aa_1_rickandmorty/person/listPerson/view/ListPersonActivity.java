package com.example.aa_1_rickandmorty.person.listPerson.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aa_1_rickandmorty.R;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.adapter.AdapterPerson;
import com.example.aa_1_rickandmorty.person.listPerson.contract.ListPersonContract;
import com.example.aa_1_rickandmorty.person.listPerson.presenter.ListPersonPresenter;
import com.example.aa_1_rickandmorty.person.searchPerson.view.SearchPersonActivity;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class ListPersonActivity extends AppCompatActivity implements ListPersonContract.View {

    private Button search_button;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private LinearLayout gif, listform;

    private ListPersonPresenter listPersonPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initComponents();

        LinearLayout.LayoutParams lpg = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams lpf = new LinearLayout.LayoutParams(0,0);
        gif.setLayoutParams(lpg);
        listform.setLayoutParams(lpf);

        System.out.println("creando el presenter");
        listPersonPresenter = new ListPersonPresenter(this);
        listPersonPresenter.getPersons();

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("saliendo a la pantalla de busqueda");
                Intent navegar = new Intent(getBaseContext(), SearchPersonActivity.class);
                startActivity(navegar);
            }
        });

    }

    private void initComponents(){
        search_button = findViewById(R.id.searchmainbutton);
        recyclerView = findViewById(R.id.recyclerMainId);
        lManager = new LinearLayoutManager(this);
        gif = findViewById(R.id.load_gif);
        listform = findViewById(R.id.list_form);
        System.out.println("inicializando los componentes");
    }

    @Override
    public void success(ArrayList<Person> listPerson){

        LinearLayout.LayoutParams lpf = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams lpg = new LinearLayout.LayoutParams(0,0);
        gif.setLayoutParams(lpg);
        listform.setLayoutParams(lpf);

        AdapterPerson adapter = new AdapterPerson(listPerson, this);
        recyclerView.setLayoutManager(lManager);
        recyclerView.setAdapter(adapter);
        System.out.println("ha salido bien");
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error,Toast.LENGTH_SHORT).show();
        System.out.println("ha salido mal");
    }
}