package com.example.josand.dc_app.UI.Main.Home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.josand.dc_app.Model.Character;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.UI.Main.Home.Adapter.HomeAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContractor.View,SwipeRefreshLayout.OnRefreshListener{

    private HomeContractor.Presenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeAdapter homeAdapter;
    private ArrayList<Character> characters;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        characters= new ArrayList<>();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if(presenter == null){
            presenter = new HomePresenter(getActivity());
        }
        initView(view);
        getPresenter().onViewAttached(HomeFragment.this);
        homeAdapter = new HomeAdapter(characters,getContext());
        recyclerView.setAdapter(homeAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    public void initView(View view){
        recyclerView = view.findViewById(R.id.homeRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshHome);
    }

    public HomeContractor.Presenter getPresenter(){
        return presenter;
    }

    public void updateAdapter() {
        homeAdapter.notifyDataSetChanged();
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
    public void onRefresh() {
        getPresenter().getCharacter(characters);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onViewDettached();
        stopRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onViewAttached(HomeFragment.this);
        if(characters.size()>0)showRecycler();
    }

    public void initData(){

        characters.add(new Character("Batman","El Caballero de la Noche"
                ,"https://images-na.ssl-images-amazon.com/images/S/cmx-images-prod/Item/366084/366084._SX1280_QL80_TTD_.jpg"));
    }

}
