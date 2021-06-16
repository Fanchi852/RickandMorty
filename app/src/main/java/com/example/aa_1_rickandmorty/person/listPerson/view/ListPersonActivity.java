package com.example.aa_1_rickandmorty.person.listPerson.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class ListPersonActivity extends AppCompatActivity implements ListPersonContract.View {

    public static final Integer INITIAL_PAGE = 1;

    private Button search_button;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private LinearLayout gif, listform;
    private ArrayList<Person> listPersonAUX = new ArrayList<>();

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

        Map<String,Object> conectdata = new HashMap<>();
        conectdata.put("context",ListPersonActivity.this);
        conectdata.put("page", INITIAL_PAGE);

        System.out.println("creando el presenter");
        listPersonPresenter = new ListPersonPresenter(this);
        listPersonPresenter.getPersons(conectdata);


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
                        listPersonPresenter.getPersons(conectdata);

                    }
                }
            }
        });
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

        //listPersonAUX.addAll(listPerson);

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
        Toast.makeText(this, error,Toast.LENGTH_SHORT).show();
        Log.e("ERROR", "Ha salido mal");
    }
}