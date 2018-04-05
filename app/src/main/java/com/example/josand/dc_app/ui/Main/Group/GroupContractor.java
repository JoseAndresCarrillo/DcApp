package com.example.josand.dc_app.ui.Main.Group;

import com.example.josand.dc_app.model.Grupo;


import java.util.ArrayList;

/**
 * Created by josan on 10/02/2018.
 */

public class GroupContractor {

    interface View {
        void updateAdapter();
        void showRecycler();
        void hideRecycler();
        void stopRefresh();
    }

    interface Presenter {
        void onViewDettached();
        void onViewAttached(GroupContractor.View view);
        ArrayList<Grupo> setUpHero();
        ArrayList<Grupo> setUpAntihero();
        ArrayList<Grupo> setUpVillian();
    }

}
