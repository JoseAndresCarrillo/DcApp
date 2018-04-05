package com.example.josand.dc_app.util;

import com.example.josand.dc_app.model.GrupoInfo;
import com.example.josand.dc_app.model.GrupoList;
import com.example.josand.dc_app.model.PersonajeInfo;
import com.example.josand.dc_app.model.PersonajeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by josan on 18/02/2018.
 */

public interface DcAPI {

    @Headers({"Content-Type: application/json"})
    @GET("personajes")
    Call<PersonajeList> getPersonajeList();

    @Headers({"Content-Type: application/json"})
    @GET("grupos")
    Call<GrupoList> getGrupoList();

    @Headers({"Content-Type: application/json"})
    @GET("personajes/{id}")
    Call<PersonajeInfo> getPersonaje(@Path("id")String id);

    @Headers({"Content-Type: application/json"})
    @GET("grupos/{id}")
    Call<GrupoInfo> getGrupo(@Path("id")String id);
}
