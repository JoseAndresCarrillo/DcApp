package com.example.josand.dc_app.UI.Details;

import android.content.Context;

import com.example.josand.dc_app.Model.Character;
import com.example.josand.dc_app.Model.Grupo;
import com.example.josand.dc_app.Model.GrupoList;
import com.example.josand.dc_app.Model.Personaje;
import com.example.josand.dc_app.Model.PersonajeList;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.Util.DcAPI;
import com.example.josand.dc_app.Util.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josan on 12/02/2018.
 */

public class DetailsPresenter  {

    private Context mContext;
    private IDetailsActivity iDetailsActivity;
    private List<Personaje> temp1,temp2,temp3;
    private List<Grupo> temp4,temp5,temp6;
    private ArrayList<Personaje> hero,villian,antihero;
    private ArrayList<Grupo> heroG,villianG,antiheroG;
    private RetrofitBuilder connection;


    public DetailsPresenter(Context mContext) {
        this.mContext = mContext;
        connection = new RetrofitBuilder(mContext,mContext.getString(R.string.BASE_URL));
        hero = new ArrayList<>();
        villian = new ArrayList<>();
        antihero = new ArrayList<>();
        heroG = new ArrayList<>();
        villianG = new ArrayList<>();
        antiheroG = new ArrayList<>();
    }

    public void onViewDettached() {
        iDetailsActivity = null;
    }

    public void onViewAttached(IDetailsActivity iDetailsActivity) {
        this.iDetailsActivity = iDetailsActivity;
    }

    public IDetailsActivity getIDetailsActivity(){
        return iDetailsActivity ;
    }


    public boolean isAttached() {
        return getIDetailsActivity() != null;
    }

    public ArrayList<Character> getCharacter(ArrayList<Character> characters) {
        if (isAttached()) iDetailsActivity.hideRecycler();
        characters.add(new Character("Superman", "https://vignette.wikia.nocookie.net/marvel_dc/images/5/56/Superman_Rebirth_Vol_1_1.jpg/revision/latest?cb=20160601112343"));
        if (isAttached()){
            iDetailsActivity.updateAdapter();
            iDetailsActivity.showRecycler();
            iDetailsActivity.stopRefresh();
        }
        return characters;
    }

    public ArrayList<Personaje> setUpHero(){
        temp1 = new ArrayList<>();
        if (isAttached())
            iDetailsActivity.hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<PersonajeList> call = service.getPersonajeList();
            call.enqueue(new Callback<PersonajeList>() {
                @Override
                public void onResponse(Call<PersonajeList> call, Response<PersonajeList> response) {
                    try {
                        hero.clear();
                        temp1 = response.body().getPersonajes();
                        for (int i=0; i<temp1.size();i++){
                            if(temp1.get(i).getType().toUpperCase().equals("HEROE"))
                                hero.add(temp1.get(i));
                        }
                        if(isAttached()){
                            iDetailsActivity.updateAdapter();
                            iDetailsActivity.showRecycler();
                            iDetailsActivity.stopRefresh();
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

    public ArrayList<Personaje> setUpVillian() {
        temp2 = new ArrayList<>();
        if (isAttached())
            iDetailsActivity.hideRecycler();
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
                            iDetailsActivity.updateAdapter();
                            iDetailsActivity.showRecycler();
                            iDetailsActivity.stopRefresh();
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

    public ArrayList<Personaje> setUpAntihero() {
        temp3 = new ArrayList<>();
        if(isAttached())
            iDetailsActivity.hideRecycler();
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
                            iDetailsActivity.updateAdapter();
                            iDetailsActivity.showRecycler();
                            iDetailsActivity.stopRefresh();
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

    public ArrayList<Grupo> setUpHeroG(){
        temp4 = new ArrayList<>();
        if (isAttached())
            iDetailsActivity.hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoList> call = service.getGrupoList();
            call.enqueue(new Callback<GrupoList>() {
                @Override
                public void onResponse(Call<GrupoList> call, Response<GrupoList> response) {
                    try {
                        heroG.clear();
                        temp4 = response.body().getGrupo();
                        for (int i=0; i<temp4.size();i++){
                            if(temp4.get(i).getType().toUpperCase().equals("HEROE"))
                                heroG.add(temp4.get(i));
                        }
                        if(isAttached()){
                            iDetailsActivity.updateAdapter();
                            iDetailsActivity.showRecycler();
                            iDetailsActivity.stopRefresh();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GrupoList> call, Throwable t) {

                }
            });
        }
        return heroG;
    }

    public ArrayList<Grupo> setUpAntiheroG(){
        temp5 = new ArrayList<>();
        if (isAttached())
            iDetailsActivity.hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoList> call = service.getGrupoList();
            call.enqueue(new Callback<GrupoList>() {
                @Override
                public void onResponse(Call<GrupoList> call, Response<GrupoList> response) {
                    try {
                        antiheroG.clear();
                        temp5 = response.body().getGrupo();
                        for (int i=0; i<temp5.size();i++){
                            if(temp5.get(i).getType().toUpperCase().equals("ANTIHEROE"))
                                antiheroG.add(temp5.get(i));
                        }
                        if(isAttached()){
                            iDetailsActivity.updateAdapter();
                            iDetailsActivity.showRecycler();
                            iDetailsActivity.stopRefresh();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GrupoList> call, Throwable t) {

                }
            });
        }
        return antiheroG;
    }

    public ArrayList<Grupo> setUpVillianG(){
        temp6 = new ArrayList<>();
        if (isAttached())
            iDetailsActivity.hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoList> call = service.getGrupoList();
            call.enqueue(new Callback<GrupoList>() {
                @Override
                public void onResponse(Call<GrupoList> call, Response<GrupoList> response) {
                    try {
                        villianG.clear();
                        temp6 = response.body().getGrupo();
                        for (int i=0; i<temp6.size();i++){
                            if(temp6.get(i).getType().toUpperCase().equals("VILLANO"))
                                villianG.add(temp6.get(i));
                        }
                        if(isAttached()){
                            iDetailsActivity.updateAdapter();
                            iDetailsActivity.showRecycler();
                            iDetailsActivity.stopRefresh();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GrupoList> call, Throwable t) {

                }
            });
        }
        return villianG;
    }

}
