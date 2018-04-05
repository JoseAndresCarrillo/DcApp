package com.example.josand.dc_app.ui.Main.Character;

import android.content.Context;

import com.example.josand.dc_app.model.Personaje;
import com.example.josand.dc_app.model.PersonajeList;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.util.DcAPI;
import com.example.josand.dc_app.util.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josan on 10/02/2018.
 */

public class CharacterPresenter implements CharacterContractor.Presenter{

    private CharacterContractor.View view;
    private Context mContext;
    private RetrofitBuilder connection;
    private ArrayList<Personaje> hero,villian,antihero;
    private List<Personaje> temp1;
    private List<Personaje> temp2;
    private List<Personaje> temp3;

    public CharacterPresenter(Context mContext) {
        this.mContext = mContext;
        connection = new RetrofitBuilder(mContext,this.mContext.getString(R.string.BASE_URL));
        hero=new ArrayList<>();
        villian=new ArrayList<>();
        antihero=new ArrayList<>();
    }


    public CharacterContractor.View getView(){
        return view;
    }


    public void onViewDettached() {
        view = null;
    }


    public void onViewAttached(CharacterContractor.View view) {
        this.view = view;
    }

    public boolean isAttached(){
        return getView() != null;
    }

    public ArrayList<Personaje> setUpHero(){
        temp1 = new ArrayList<>();
        if (isAttached())
            getView().hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<PersonajeList> call = service.getPersonajeList();
            call.enqueue(new Callback<PersonajeList>() {
                @Override
                public void onResponse(Call<PersonajeList> call, Response<PersonajeList> response) {
                    try {
                        hero.clear();
                        temp1 = response.body().getPersonajes();
                        for (int i=0; i<5;i++){
                            if(temp1.get(i).getType().toUpperCase().equals("HEROE"))
                                hero.add(temp1.get(i));
                        }
                        if(isAttached()){
                            getView().updateAdapter();
                            getView().showRecycler();
                            getView().stopRefresh();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<PersonajeList> call, Throwable t) {

                }
            });
        }
        return hero;
    }

    @Override
    public ArrayList<Personaje> setUpVillian() {
        temp2 = new ArrayList<>();
        if (isAttached())
            getView().hideRecycler();
        if (connection.getRetrofit()!=null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<PersonajeList> call = service.getPersonajeList();
            call.enqueue(new Callback<PersonajeList>() {
                @Override
                public void onResponse(Call<PersonajeList> call, Response<PersonajeList> response) {
                    try {
                        villian.clear();
                        temp2 = response.body().getPersonajes();
                        for (int i=0; i<temp2.size();i++){
                            if(temp2.get(i).getType().toUpperCase().equals("VILLANO"))
                                    villian.add(temp2.get(i));

                        }
                        if(isAttached()){
                            getView().updateAdapter();
                            getView().showRecycler();
                            getView().stopRefresh();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<PersonajeList> call, Throwable t) {

                }
            });
        }
        return villian;
    }

    @Override
    public ArrayList<Personaje> setUpAntihero() {
        temp3 = new ArrayList<>();
        if(isAttached())
            getView().hideRecycler();
        if (connection.getRetrofit()!=null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<PersonajeList> call = service.getPersonajeList();
            call.enqueue(new Callback<PersonajeList>() {
                @Override
                public void onResponse(Call<PersonajeList> call, Response<PersonajeList> response) {
                    try {
                        antihero.clear();
                        temp3 = response.body().getPersonajes();
                        for (int i=0; i<temp3.size();i++){
                            if(temp3.get(i).getType().toUpperCase().equals("ANTIHEROE"))
                                antihero.add(temp3.get(i));
                        }
                        if(isAttached()){
                            getView().updateAdapter();
                            getView().showRecycler();
                            getView().stopRefresh();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<PersonajeList> call, Throwable t) {

                }
            });
        }
        return antihero;
    }

}
