package com.example.josand.dc_app.UI.Details;

import com.example.josand.dc_app.Model.Character;

import java.util.ArrayList;

/**
 * Created by josan on 12/02/2018.
 */

public interface IDetailsActivity {

        void updateAdapter();
        void showRecycler();
        void hideRecycler();
        void stopRefresh();

}
