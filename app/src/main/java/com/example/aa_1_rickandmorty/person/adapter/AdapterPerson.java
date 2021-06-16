package com.example.aa_1_rickandmorty.person.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa_1_rickandmorty.R;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.listPerson.view.ListPersonActivity;
import com.example.aa_1_rickandmorty.person.viewPerson.view.ViewPersonActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPerson extends RecyclerView.Adapter<AdapterPerson.ViewHolderCharacter> {

    private ArrayList<Person> persons;
    private Activity activity;
    private Integer positionAUX;
    private Integer lastpagueloaded = ListPersonActivity.INITIAL_PAGE;
    private Boolean loadindata = false;


    public AdapterPerson(ArrayList<Person> person,Activity activity) {
        this.persons = person;
        this.activity = activity;
    }

    public void addPersons(ArrayList<Person> persons){
        this.persons.addAll(persons);
    }

    @Override
    public ViewHolderCharacter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_main_list,parent,false);
        return new ViewHolderCharacter(view);
    }

    @Override
    public void onBindViewHolder(AdapterPerson.ViewHolderCharacter holder, int position) {
        holder.asingPerson(persons.get(position));
        System.out.println("estamos en el onbindviewholder getItemCount: " + getItemCount());
        System.out.println("estamos en el onbindviewholder position: " + position);
        positionAUX = position;
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String persontojson = gson.toJson(persons.get(position));
                Intent intent = new Intent(activity, ViewPersonActivity.class);
                intent.putExtra("PERSON_DATA", persontojson);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, "transitionnamepicture").toBundle());
                }else {
                    activity.startActivity(intent);
                }
            }
        });
    }

    public Integer getLastpagueloaded() {
        return lastpagueloaded;
    }

    public void setLastpagueloaded(Integer lastpagueloaded) {
        this.loadindata = true;
        this.lastpagueloaded = lastpagueloaded;
    }

    public Integer getPositionLastLoad(){
        return positionAUX;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public boolean isLoading() {
        return this.loadindata;
    }

    public void setLoadingData(boolean loading) {
        Log.i("informaccion de mierda","ententamos entrar el set: " + this.loadindata);
        this.loadindata = loading;
        Log.i("informaccion de mierda","ententamos saliendo el set: " + this.loadindata);
    }

    public class ViewHolderCharacter extends RecyclerView.ViewHolder {

        public ImageView profileimage;
        public TextView textViewframename, textViewframespecie;
        private LinearLayout linearLayout;

        public ViewHolderCharacter(View itemView) {
            super(itemView);
            profileimage = itemView.findViewById(R.id.profile_image);
            textViewframename = itemView.findViewById(R.id.textView_frame_name);
            textViewframespecie = itemView.findViewById(R.id.textView_frame_specie);
            linearLayout = itemView.findViewById(R.id.frame_card);
        }

        public void asingPerson(Person person) {
            Picasso.get().load(person.getImage()).noFade().into(profileimage);
            textViewframename.setText(person.getName());
            textViewframespecie.setText(person.getSpecies());
        }
    }
}
















