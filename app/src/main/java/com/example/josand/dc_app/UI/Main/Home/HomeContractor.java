package com.example.josand.dc_app.UI.Main.Home;

import com.example.josand.dc_app.Model.Character;

import java.util.ArrayList;

/**
 * Created by josan on 9/02/2018.
 */

public class HomeContractor {

    interface View{
        void updateAdapter();
        void showRecycler();
        void hideRecycler();
        void stopRefresh();
    }

    interface Presenter{
        void onViewDettached();
        void onViewAttached(HomeContractor.View view);
        ArrayList<Character> getCharacter(ArrayList<Character> characters);
    }

}
