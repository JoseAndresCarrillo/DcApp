package com.example.josand.dc_app.ui.Main.Character;


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

import com.example.josand.dc_app.model.Personaje;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.ui.Main.Adapter.ItemAdapter;
import com.example.josand.dc_app.ui.Details.DetailsActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterFragment extends Fragment implements CharacterContractor.View, SwipeRefreshLayout.OnRefreshListener {

    private CharacterContractor.Presenter presenter;
    RecyclerView recyclerViewHero;
    RecyclerView recyclerViewVillian;
    RecyclerView recyclerViewAntiHero;
    SwipeRefreshLayout swipeRefreshLayout;
    ItemAdapter heroAdapter;
    ItemAdapter villianAdapter;
    ItemAdapter antiheroAdapter;
    ArrayList<Personaje> itemsHero;
    ArrayList<Personaje> itemsVillian;
    ArrayList<Personaje> itemsAntihero;
    TextView moreHeroes, moreVillians, moreAntiHeroes;


    public CharacterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        initView(view);
        if (presenter == null) {
            presenter = new CharacterPresenter(getActivity());
        }
        getPresenter().onViewAttached(CharacterFragment.this);
        setUpRecyclerView();
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    public void initView(View view) {
        recyclerViewHero = view.findViewById(R.id.recyclerViewHero);
        recyclerViewVillian = view.findViewById(R.id.recyclerViewVillian);
        recyclerViewAntiHero = view.findViewById(R.id.recyclerViewAntiHero);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshCharacter);
        moreHeroes = view.findViewById(R.id.seeMoreHero);
        moreVillians = view.findViewById(R.id.seeMoreVillian);
        moreAntiHeroes = view.findViewById(R.id.seeMoreAntiHero);
        onClickHero();
        onClickVillian();
        onClickAntihero();
    }

    public void setUpRecyclerView() {
        itemsHero = getPresenter().setUpHero();
        itemsVillian = getPresenter().setUpVillian();
        itemsAntihero = getPresenter().setUpAntihero();
        //Hero
        heroAdapter = new ItemAdapter(itemsHero, getActivity());
        recyclerViewHero.setAdapter(heroAdapter);
        RecyclerView.LayoutManager heroLM = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHero.setLayoutManager(heroLM);
        //Villian
        villianAdapter = new ItemAdapter(itemsVillian, getActivity());
        recyclerViewVillian.setAdapter(villianAdapter);
        RecyclerView.LayoutManager villianLM = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewVillian.setLayoutManager(villianLM);
        //Antihero
        antiheroAdapter = new ItemAdapter(itemsAntihero, getActivity());
        recyclerViewAntiHero.setAdapter(antiheroAdapter);
        RecyclerView.LayoutManager antiheroLM = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewAntiHero.setLayoutManager(antiheroLM);
    }

    public CharacterContractor.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void updateAdapter() {
        heroAdapter.notifyDataSetChanged();
        villianAdapter.notifyDataSetChanged();
        antiheroAdapter.notifyDataSetChanged();
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
    public void onPause() {
        super.onPause();
        getPresenter().onViewDettached();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onViewAttached(CharacterFragment.this);
        if (itemsHero.size() > 0) showRecycler();
        if (itemsVillian.size() > 0) showRecycler();
        if (itemsAntihero.size() > 0) showRecycler();
    }

    @Override
    public void onRefresh() {
        getPresenter().setUpHero();
        getPresenter().setUpVillian();
        getPresenter().setUpAntihero();
    }

    public void onClickHero() {
        moreHeroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("title","Heroes");
                startActivity(intent);
            }
        });
    }

    public void onClickVillian() {
        moreVillians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("title","Villanos");
                startActivity(intent);
            }
        });
    }
    public void onClickAntihero() {
        moreAntiHeroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("title","Antiheroes");
                startActivity(intent);
            }
        });
    }

}
