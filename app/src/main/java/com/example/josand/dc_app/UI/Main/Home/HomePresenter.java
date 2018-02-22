package com.example.josand.dc_app.UI.Main.Home;

import android.content.Context;

import com.example.josand.dc_app.Model.Character;

import java.util.ArrayList;

/**
 * Created by josan on 9/02/2018.
 */

public class HomePresenter implements HomeContractor.Presenter {

    private HomeContractor.View view;
    //private ArrayList<Character> characters;
    private Context mContext;

    HomePresenter(Context mContext) {
        this.mContext = mContext;
        //characters = new ArrayList<>();
    }

    public void onViewDettached() {
        view = null;
    }


    public void onViewAttached(HomeContractor.View view) {
        this.view = view;
    }


    public ArrayList<Character> getCharacter(ArrayList<Character> characters) {
        if(isAttached())getView().hideRecycler();
        characters.add(new Character("Superman","El Hombre de Acero","https://vignette.wikia.nocookie.net/marvel_dc/images/5/56/Superman_Rebirth_Vol_1_1.jpg/revision/latest?cb=20160601112343"));
        if(isAttached()){
            getView().updateAdapter();
            getView().showRecycler();
            getView().stopRefresh();
        }
        return characters;
    }

    public HomeContractor.View getView(){
        return view;
    }

    public boolean isAttached(){
        return getView() != null;
    }


}
