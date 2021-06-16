package com.example.aa_1_rickandmorty.person.searchPerson.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.aa_1_rickandmorty.person.listPerson.view.ListPersonActivity;
import com.example.aa_1_rickandmorty.person.searchPerson.contract.SearchPersonContract;
import com.example.aa_1_rickandmorty.person.searchPerson.presenter.SearchPersonPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchPersonActivity extends AppCompatActivity implements SearchPersonContract.View {

    public static final Integer INITIAL_PAGE = 1;

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

        Map<String,Object> conectdata = new HashMap<>();
        conectdata.put("context", SearchPersonActivity.this);
        conectdata.put("page", INITIAL_PAGE);

        searchPersonPresenter = new SearchPersonPresenter(this);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                conectdata.put("name",search_txt.getText().toString());
                searchPersonPresenter.getPersons(conectdata);

            }
        });

        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                AdapterPerson adapter = null;


                if(recyclerView.getAdapter() instanceof AdapterPerson){
                    adapter = (AdapterPerson) recyclerView.getAdapter();
                }else{
                    Log.e("ERROR", "El adapter no es lo esperado");
                }
                Log.i("informaccion de mierda","ententamos entrar en una nueva llamada: " + adapter.isLoading());
                if(adapter != null && !adapter.isLoading()){
                    Integer maxCount = adapter.getItemCount();
                    Integer actualCount = adapter.getPositionLastLoad();
                    Log.i("informaccion de mierda","maxCount: " + maxCount);
                    Log.i("informaccion de mierda","actualCount: " + actualCount);
                    if (actualCount >= maxCount - 20){
                        System.out.println("cargando mas cositas");
                        Integer nextpage = adapter.getLastpagueloaded()+1;
                        Log.i("informaccion de mierda","ententamos entrar el set: " + nextpage);
                        adapter.setLastpagueloaded(nextpage);
                        conectdata.replace("page",nextpage);
                        //listPersonPresenter = new ListPersonPresenter(ListPersonActivity.this);
                        searchPersonPresenter.getPersons(conectdata);

                    }
                }
            }
        });

    }

    @Override
    public void success(ArrayList<Person> listPerson) {
        AdapterPerson adapter = null;

        if(recyclerView.getAdapter() != null){
            adapter = (AdapterPerson) recyclerView.getAdapter();
            adapter.addPersons(listPerson);
        }else{
            adapter = new AdapterPerson(listPerson, this);
            recyclerView.setLayoutManager(lManager);
            recyclerView.setAdapter(adapter);
        }

        adapter.setLoadingData(false);
        System.out.println("ha salido bien");
    }

    @Override
    public void error(String error) {
        AdapterPerson adapter = null;

        if(recyclerView.getAdapter() != null){
            adapter = (AdapterPerson) recyclerView.getAdapter();
            adapter.setLoadingData(false);
        }
        Log.e("ERROR", "Ha salido mal");
        Toast.makeText(this, error,Toast.LENGTH_SHORT).show();
    }

    private void initComponents(){

        search_button = findViewById(R.id.searchbutton);
        recyclerView = findViewById(R.id.recyclerSearchId);
        lManager = new LinearLayoutManager(this);
        search_txt = findViewById(R.id.nombresearchtxt);

    }
}
