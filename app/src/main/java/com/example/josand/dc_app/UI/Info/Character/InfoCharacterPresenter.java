package com.example.josand.dc_app.UI.Info.Character;

import android.content.Context;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.josand.dc_app.Model.Character;
import com.example.josand.dc_app.Model.PersonajeInfo;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.UI.Info.Character.IInfoCharacterActivity;
import com.example.josand.dc_app.Util.DcAPI;
import com.example.josand.dc_app.Util.RetrofitBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josan on 11/02/2018.
 */

public class InfoCharacterPresenter {
    private Context mContext;
    private RetrofitBuilder connection;
    private IInfoCharacterActivity iInfoCharacterActivity;
    private Character character;
    private PersonajeInfo info;
    private ArrayList<PersonajeInfo> personajeInfos;
    private String id;

    public InfoCharacterPresenter(Context mContext, String id) {
        this.mContext = mContext;
        this.id = id;
        connection = new RetrofitBuilder(mContext, this.mContext.getString(R.string.BASE_URL));
        info = new PersonajeInfo();
        personajeInfos = new ArrayList<>();
    }


    public PersonajeInfo setUpPersonaje() {
        if (connection.getRetrofit() != null) {
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<PersonajeInfo> call = service.getPersonaje(id);
            call.enqueue(new Callback<PersonajeInfo>() {
                @Override
                public void onResponse(Call<PersonajeInfo> call, Response<PersonajeInfo> response) {
                    info = response.body();
                }

                @Override
                public void onFailure(Call<PersonajeInfo> call, Throwable t) {
                    Toast.makeText(mContext,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return info;
    }

    public void onViewDettached() {
        iInfoCharacterActivity = null;
    }


    public void onViewAttached(IInfoCharacterActivity iInfoCharacterActivity) {
        this.iInfoCharacterActivity = iInfoCharacterActivity;
    }
}
