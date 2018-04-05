package com.example.josand.dc_app.ui.Main.Group;

import android.content.Context;

import com.example.josand.dc_app.model.Grupo;
import com.example.josand.dc_app.model.GrupoList;
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

public class GroupPresenter implements GroupContractor.Presenter {

    private GroupContractor.View view;
    private Context mContext;
    private RetrofitBuilder connection;
    private List<Grupo> temp1,temp2,temp3;
    private ArrayList<Grupo> hero,antihero,villian;

    public GroupPresenter(Context mContext) {
        this.mContext = mContext;
        connection = new RetrofitBuilder(mContext,this.mContext.getString(R.string.BASE_URL));
        hero = new ArrayList<>();
        antihero = new ArrayList<>();
        villian = new ArrayList<>();
    }

    public void onViewDettached() {
        view = null;
    }

    @Override
    public void onViewAttached(GroupContractor.View view) {
        this.view = view;
    }

    public boolean isAttached(){
        return getView() != null;
    }

    public GroupContractor.View getView(){
        return view;
    }

    public ArrayList<Grupo> setUpHero(){
        temp1 = new ArrayList<>();
        if (isAttached())
            getView().hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoList> call = service.getGrupoList();
            call.enqueue(new Callback<GrupoList>() {
                @Override
                public void onResponse(Call<GrupoList> call, Response<GrupoList> response) {
                    try {
                        hero.clear();
                        temp1 = response.body().getGrupo();
                        for (int i=0; i<temp1.size();i++){
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
                public void onFailure(Call<GrupoList> call, Throwable t) {

                }
            });
        }
        return hero;
    }

    public ArrayList<Grupo> setUpAntihero(){
        temp2 = new ArrayList<>();
        if (isAttached())
            getView().hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoList> call = service.getGrupoList();
            call.enqueue(new Callback<GrupoList>() {
                @Override
                public void onResponse(Call<GrupoList> call, Response<GrupoList> response) {
                    try {
                        antihero.clear();
                        temp2 = response.body().getGrupo();
                        for (int i=0; i<temp2.size();i++){
                            if(temp2.get(i).getType().toUpperCase().equals("ANTIHEROE"))
                                antihero.add(temp2.get(i));
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
                public void onFailure(Call<GrupoList> call, Throwable t) {

                }
            });
        }
        return antihero;
    }

    public ArrayList<Grupo> setUpVillian(){
        temp3 = new ArrayList<>();
        if (isAttached())
            getView().hideRecycler();
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoList> call = service.getGrupoList();
            call.enqueue(new Callback<GrupoList>() {
                @Override
                public void onResponse(Call<GrupoList> call, Response<GrupoList> response) {
                    try {
                        villian.clear();
                        temp3 = response.body().getGrupo();
                        for (int i=0; i<temp3.size();i++){
                            if(temp3.get(i).getType().toUpperCase().equals("VILLANO"))
                                villian.add(temp3.get(i));
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
                public void onFailure(Call<GrupoList> call, Throwable t) {

                }
            });
        }
        return villian;
    }
}
