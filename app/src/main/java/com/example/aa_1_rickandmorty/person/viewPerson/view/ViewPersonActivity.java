package com.example.aa_1_rickandmorty.person.viewPerson.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aa_1_rickandmorty.R;
import com.example.aa_1_rickandmorty.beans.Episode;
import com.example.aa_1_rickandmorty.beans.Location;
import com.example.aa_1_rickandmorty.beans.Person;
import com.example.aa_1_rickandmorty.person.viewPerson.contract.ViewPersonContract;
import com.example.aa_1_rickandmorty.person.viewPerson.presentar.ViewPersonPresenter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ViewPersonActivity extends AppCompatActivity implements ViewPersonContract.View {

    private TextView txtName, txtStatus, txtSpecies, txtType, txtGender, txtOrigin, txtLocation;
    private ImageView profileimage;

    private ViewPersonPresenter viewPersonPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        String jsonPerson = getIntent().getStringExtra("PERSON_DATA");
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonPerson, Person.class);

        initComponents();

        Picasso.get().load(person.getImage()).noFade().into(profileimage);
        txtName.setText(person.getName());
        txtStatus.setText(person.getStatus());
        txtSpecies.setText(person.getSpecies());
        txtType.setText(person.getType());
        txtGender.setText(person.getGender());
        txtOrigin.setText(person.getLocation().getName());
        txtLocation.setText(person.getOrigin().getName());

    }

    private void initComponents(){
        profileimage = findViewById(R.id.profile_image);
        txtName = findViewById(R.id.character_name_view);
        txtStatus = findViewById(R.id.Character_status_view);
        txtSpecies = findViewById(R.id.Character_specie_view);
        txtType = findViewById(R.id.Character_type_view);
        txtGender = findViewById(R.id.Character_gender_view);
        txtOrigin = findViewById(R.id.Character_origin_view);
        txtLocation = findViewById(R.id.Character_location_view);

    }

    @Override
    public void successEpisode(Episode episode) {

    }

    @Override
    public void successLocation(Location location) {

    }

    @Override
    public void error(String error) {
        Toast.makeText(this, error,Toast.LENGTH_SHORT).show();
    }
}
