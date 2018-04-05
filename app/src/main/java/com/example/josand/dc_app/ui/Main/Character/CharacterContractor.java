package com.example.josand.dc_app.ui.Main.Character;

import com.example.josand.dc_app.model.Personaje;

import java.util.ArrayList;

/**
 * Created by josan on 10/02/2018.
 */

public class CharacterContractor {

    interface View{
        void updateAdapter();
        void showRecycler();
        void hideRecycler();
        void stopRefresh();
    }

    interface Presenter{
        void onViewDettached();
        void onViewAttached(CharacterContractor.View view);
        ArrayList<Personaje> setUpHero();
        ArrayList<Personaje> setUpVillian();
        ArrayList<Personaje> setUpAntihero();
    }
}
