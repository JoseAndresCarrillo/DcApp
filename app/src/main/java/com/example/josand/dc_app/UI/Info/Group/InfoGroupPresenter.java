package com.example.josand.dc_app.UI.Info.Group;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;

import com.example.josand.dc_app.Model.Character;
import com.example.josand.dc_app.Model.Group;
import com.example.josand.dc_app.Model.GrupoInfo;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.UI.Info.Character.InfoCharacterActivity;
import com.example.josand.dc_app.Util.DcAPI;
import com.example.josand.dc_app.Util.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josan on 13/02/2018.
 */

public class InfoGroupPresenter {
    private Context mContext;
    private RetrofitBuilder connection;
    private IInfoGroupActivity iInfoGroupActivity;
    private GrupoInfo grupoInfo;
    private String id;


    public InfoGroupPresenter(Context mContext, String id) {
        this.mContext = mContext;
        this.id = id;
        connection = new RetrofitBuilder(mContext,mContext.getString(R.string.BASE_URL));
        grupoInfo = new GrupoInfo();
    }

    public GrupoInfo getGroup(){
        if (connection.getRetrofit()!= null){
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoInfo> call = service.getGrupo(id);
            call.enqueue(new Callback<GrupoInfo>() {
                @Override
                public void onResponse(Call<GrupoInfo> call, Response<GrupoInfo> response) {
                    grupoInfo = response.body();
                }

                @Override
                public void onFailure(Call<GrupoInfo> call, Throwable t) {

                }
            });
        }
        return grupoInfo;
    }

    public void onClickTag(TagGroup tagGroup){
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                Intent intent = new Intent(mContext, InfoCharacterActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    public void onViewDettached() {
        iInfoGroupActivity = null;
    }


    public void onViewAttached(IInfoGroupActivity iInfoCharacterActivity) {
        this.iInfoGroupActivity = iInfoCharacterActivity;
    }
}
