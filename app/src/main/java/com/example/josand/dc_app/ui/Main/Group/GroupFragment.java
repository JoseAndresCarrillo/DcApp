package com.example.josand.dc_app.ui.Main.Group;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.josand.dc_app.model.Grupo;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.ui.Details.DetailsActivity;
import com.example.josand.dc_app.ui.Main.Adapter.GroupAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment implements GroupContractor.View, SwipeRefreshLayout.OnRefreshListener {

    private GroupContractor.Presenter presenter;
    RecyclerView recyclerViewHG;
    RecyclerView recyclerViewVG;
    RecyclerView recyclerViewAG;
    SwipeRefreshLayout swipeRefreshLayout;
    GroupAdapter heroGroupAdapter;
    GroupAdapter villianGroupAdapter;
    GroupAdapter antiheroGroupAdapter;
    ArrayList<Grupo> itemsHG;
    ArrayList<Grupo> itemsVG;
    ArrayList<Grupo> itemsAG;
    TextView moreHeroes, moreVillians, moreAntiHeroes;


    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group, container, false);

        initViews(view);
        if (getPresenter()==null){
            presenter = new GroupPresenter(getActivity());
        }
        getPresenter().onViewAttached(GroupFragment.this);
        setUpRecyclerViews();
        swipeRefreshLayout.setOnRefreshListener(this);


        return view;
    }

    public void initViews(View view){
        recyclerViewHG = view.findViewById(R.id.recyclerViewHeroGroup);
        recyclerViewVG = view.findViewById(R.id.recyclerViewVillianGroup);
        recyclerViewAG = view.findViewById(R.id.recyclerViewAntiHeroGroup);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshGroup);
        moreHeroes = view.findViewById(R.id.seeMoreHeroGroup);
        moreVillians = view.findViewById(R.id.seeMoreVillianGroup);
        moreAntiHeroes = view.findViewById(R.id.seeMoreAntiHeroGroup);
        onClickHero();
        onClickVillian();
        onClickAntihero();
    }

    public void setUpRecyclerViews(){
        itemsHG = getPresenter().setUpHero();
        itemsVG = getPresenter().setUpVillian();
        itemsAG = getPresenter().setUpAntihero();
        //Hero Group
        heroGroupAdapter = new GroupAdapter(itemsHG,getActivity());
        recyclerViewHG.setAdapter(heroGroupAdapter);
        RecyclerView.LayoutManager heroGroupLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewHG.setLayoutManager(heroGroupLayoutManager);
        //Villian Group
        villianGroupAdapter = new GroupAdapter(itemsVG,getActivity());
        recyclerViewVG.setAdapter(villianGroupAdapter);
        RecyclerView.LayoutManager villianGroupLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewVG.setLayoutManager(villianGroupLayoutManager);
        //Antihero Group
        antiheroGroupAdapter = new GroupAdapter(itemsAG,getActivity());
        recyclerViewAG.setAdapter(antiheroGroupAdapter);
        RecyclerView.LayoutManager antiheroGroupLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewAG.setLayoutManager(antiheroGroupLayoutManager);
    }

    @Override
    public void updateAdapter() {
        heroGroupAdapter.notifyDataSetChanged();
        villianGroupAdapter.notifyDataSetChanged();
        antiheroGroupAdapter.notifyDataSetChanged();
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

    public GroupContractor.Presenter getPresenter(){
        return presenter;
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onViewDettached();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onViewAttached(GroupFragment.this);
        if(itemsHG.size()>0) showRecycler();
        if(itemsVG.size()>0) showRecycler();
        if(itemsAG.size()>0) showRecycler();
    }

    @Override
    public void onRefresh() {
        getPresenter().setUpHero();
        getPresenter().setUpAntihero();
        getPresenter().setUpVillian();
    }


    public void onClickHero() {
        moreHeroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("title","Grupos de Heroes");
                startActivity(intent);
            }
        });
    }

    public void onClickVillian() {
        moreVillians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("title","Grupos de Villanos");
                startActivity(intent);
            }
        });
    }
    public void onClickAntihero() {
        moreAntiHeroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("title","Grupos de Antiheroes");
                startActivity(intent);
            }
        });
    }
}
