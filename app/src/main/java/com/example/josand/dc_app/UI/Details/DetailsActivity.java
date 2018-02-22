package com.example.josand.dc_app.UI.Details;


import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.josand.dc_app.Model.Character;
import com.example.josand.dc_app.Model.Grupo;
import com.example.josand.dc_app.Model.Personaje;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.UI.Details.Adapter.CharacterDetailsAdapter;
import com.example.josand.dc_app.UI.Details.Adapter.GroupDetailsAdapter;

import java.util.ArrayList;


public class DetailsActivity extends AppCompatActivity implements IDetailsActivity,SwipeRefreshLayout.OnRefreshListener{

    private DetailsPresenter presenter;
    private GridView gridView;
    private ArrayList<Character> characters;
    private ArrayList<Personaje> personajes;
    private ArrayList<Grupo> grupos;
    private CharacterDetailsAdapter characterDetailsAdapter;
    private GroupDetailsAdapter groupDetailsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String title;
    private IDetailsActivity iDetailsActivity;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title =  getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);
        if(presenter ==null){
            presenter = new DetailsPresenter(getApplicationContext());
        }
        initView();
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return  true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                characterDetailsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void initView(){
        personajes = new ArrayList<>();
        grupos = new ArrayList<>();
        gridView = findViewById(R.id.detailGridView);
        setArrayListOnAdapter(title);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshDetails);
    }

    public DetailsPresenter getPresenter(){
        return presenter;
    }



    @Override
    public void onRefresh() {
        switch (title){
            case "Heroes":
                personajes.clear();
                personajes= getPresenter().setUpHero();
                break;
            case "Villanos":
                personajes.clear();
                personajes= getPresenter().setUpVillian();
                break;
            case "Antiheroes":
                personajes.clear();
                personajes = getPresenter().setUpAntihero();
                break;
            case "Grupos de Heroes":
                grupos.clear();
                grupos = getPresenter().setUpHeroG();
                break;
            case "Grupos de Villanos":
                grupos.clear();
                grupos = getPresenter().setUpVillianG();
                break;
            case "Grupos de Antiheroes":
                grupos.clear();
                grupos = getPresenter().setUpAntiheroG();
                break;
        }
    }

    @Override
    public void updateAdapter() {
        switch (title){
            case "Heroes":
                characterDetailsAdapter.notifyDataSetChanged();
                break;
            case "Villanos":
                characterDetailsAdapter.notifyDataSetChanged();
                break;
            case "Antiheroes":
                characterDetailsAdapter.notifyDataSetChanged();
                break;
            case "Grupos de Heroes":
                groupDetailsAdapter.notifyDataSetChanged();
                break;
            case "Grupos de Villanos":
                groupDetailsAdapter.notifyDataSetChanged();
                break;
            case "Grupos de Antiheroes":
                groupDetailsAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void showRecycler() {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecycler() {
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onViewAttached(DetailsActivity.this);
        if (personajes.size()>0)
            showRecycler();
        else if (grupos.size()>0)
            showRecycler();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onViewDettached();
    }

    public void setArrayListOnAdapter(String title){
        switch (title){
            case "Heroes":
                personajes.clear();
                personajes= getPresenter().setUpHero();
                characterDetailsAdapter= new CharacterDetailsAdapter(personajes,getApplicationContext());
                gridView.setAdapter(characterDetailsAdapter);
                break;
            case "Villanos":
                personajes.clear();
                personajes= getPresenter().setUpVillian();
                characterDetailsAdapter= new CharacterDetailsAdapter(personajes,getApplicationContext());
                gridView.setAdapter(characterDetailsAdapter);
                break;
            case "Antiheroes":
                personajes.clear();
                personajes = getPresenter().setUpAntihero();
                characterDetailsAdapter= new CharacterDetailsAdapter(personajes,getApplicationContext());
                gridView.setAdapter(characterDetailsAdapter);
                break;
            case "Grupos de Heroes":
                grupos.clear();
                grupos = getPresenter().setUpHeroG();
                groupDetailsAdapter= new GroupDetailsAdapter(grupos,getApplicationContext());
                gridView.setAdapter(groupDetailsAdapter);
                break;
            case "Grupos de Villanos":
                grupos.clear();
                grupos = getPresenter().setUpVillianG();
                groupDetailsAdapter= new GroupDetailsAdapter(grupos,getApplicationContext());
                gridView.setAdapter(groupDetailsAdapter);
                break;
            case "Grupos de Antiheroes":
                grupos.clear();
                grupos = getPresenter().setUpAntiheroG();
                groupDetailsAdapter= new GroupDetailsAdapter(grupos,getApplicationContext());
                gridView.setAdapter(groupDetailsAdapter);
                break;
        }
    }

    public void initData(){
        characters = new ArrayList<>();
        characters.add(new Character("Batman","https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/366084/366084._SX1280_QL80_TTD_.jpg"));
        characters.add(new Character("Wonder Woman","https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/366084/366084._SX1280_QL80_TTD_.jpg"));
        characters.add(new Character("The Flash","https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/366084/366084._SX1280_QL80_TTD_.jpg"));
        characters.add(new Character("Green Lantern","https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/366084/366084._SX1280_QL80_TTD_.jpg"));
        characters.add(new Character("Aquaman","https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/366084/366084._SX1280_QL80_TTD_.jpg"));
    }
}
