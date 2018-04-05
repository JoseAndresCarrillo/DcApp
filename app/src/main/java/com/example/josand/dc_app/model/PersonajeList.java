package com.example.josand.dc_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by josan on 18/02/2018.
 */

public class PersonajeList {

    @SerializedName("personajes")
    @Expose
    private List<Personaje> personajes = null;

    public PersonajeList() {

    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

}
