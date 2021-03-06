package com.example.josand.dc_app.ui.Info.Group;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.josand.dc_app.model.GrupoInfo;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.ui.Info.Character.InfoCharacterActivity;
import com.example.josand.dc_app.util.DcAPI;
import com.example.josand.dc_app.util.RetrofitBuilder;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josan on 13/02/2018.
 */

public class InfoGroupPresenter implements InfoGroupContractor.Presenter {
    private Context mContext;
    private RetrofitBuilder connection;
    private InfoGroupContractor.View view;
    private GrupoInfo grupoInfo;
    private String id;


    public InfoGroupPresenter(Context mContext, String id) {
        this.mContext = mContext;
        this.id = id;
        connection = new RetrofitBuilder(mContext, mContext.getString(R.string.BASE_URL));
        grupoInfo = new GrupoInfo();
    }

    @Override
    public void onViewAttached(InfoGroupContractor.View view) {
        this.view = view;
    }

    @Override
    public void onViewDettached() {
        view = null;
    }

    private boolean isAttached() {
        return getView() != null;
    }

    @Nullable
    private InfoGroupContractor.View getView() {
        return view;
    }

    public void getGroup() {
        if (connection.getRetrofit() != null) {
            DcAPI service = connection.getRetrofit().create(DcAPI.class);
            Call<GrupoInfo> call = service.getGrupo(id);
            call.enqueue(new Callback<GrupoInfo>() {
                @Override
                public void onResponse(Call<GrupoInfo> call, Response<GrupoInfo> response) {
                    grupoInfo = response.body();
                    if(isAttached()){
                        getView().setUpData(grupoInfo);
                    }
                }

                @Override
                public void onFailure(Call<GrupoInfo> call, Throwable t) {

                }
            });
        }
    }

    public void onClickTag(TagGroup tagGroup) {
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                Intent intent = new Intent(mContext, InfoCharacterActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }


}
