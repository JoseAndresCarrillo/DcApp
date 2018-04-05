package com.example.josand.dc_app.ui.Info.Character;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.josand.dc_app.model.Character;
import com.example.josand.dc_app.model.PersonajeInfo;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.util.DcAPI;
import com.example.josand.dc_app.util.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josan on 11/02/2018.
 */

public class InfoCharacterPresenter implements InfoCharacterContractor.presenter{
    private Context mContext;
    private RetrofitBuilder connection;
    private InfoCharacterContractor.view view;
    private PersonajeInfo info;
    private String id;

    public InfoCharacterPresenter(Context mContext, String id) {
        this.mContext = mContext;
        this.id = id;
        connection = new RetrofitBuilder(mContext, this.mContext.getString(R.string.BASE_URL));
        info = new PersonajeInfo();
    }


    public void getPersonaje() {
        if (connection.getRetrofit() != null) {
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<PersonajeInfo> call = service.getPersonaje(id);
            call.enqueue(new Callback<PersonajeInfo>() {
                @Override
                public void onResponse(Call<PersonajeInfo> call, Response<PersonajeInfo> response) {
                    info = response.body();
                    if (isAttached()){
                        getView().setUpData(info);
                    }
                }

                @Override
                public void onFailure(Call<PersonajeInfo> call, Throwable t) {
                    Toast.makeText(mContext,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public InfoCharacterContractor.view getView(){
        return view;
    }

    public void onViewDettached() {
        view = null;
    }

    public void onViewAttached(InfoCharacterContractor.view view) {
        this.view=view;
    }
    public boolean isAttached(){
        return getView() != null;
    }

}
