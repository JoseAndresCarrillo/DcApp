package com.example.josand.dc_app.ui.Info.Character;

import com.example.josand.dc_app.model.Character;
import com.example.josand.dc_app.model.PersonajeInfo;
import com.example.josand.dc_app.ui.Info.Group.InfoGroupContractor;

/**
 * Created by josan on 11/02/2018.
 */

public class InfoCharacterContractor {
    public interface view{
        void setUpData(PersonajeInfo personajeInfo);
    }
    public interface presenter{
        void onViewDettached();
        void onViewAttached(InfoCharacterContractor.view view);
        void getPersonaje();
    }
}
