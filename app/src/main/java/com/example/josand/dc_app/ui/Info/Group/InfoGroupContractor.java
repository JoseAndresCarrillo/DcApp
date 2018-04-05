package com.example.josand.dc_app.ui.Info.Group;

import com.example.josand.dc_app.model.GrupoInfo;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by josan on 13/02/2018.
 */

public class InfoGroupContractor {
    public interface View{
        void setUpData(GrupoInfo groupinfo);

    }
    public interface Presenter{
        void onViewDettached();
        void onViewAttached(InfoGroupContractor.View view);
        void getGroup();
        void onClickTag(TagGroup tagGroup);
    }
}
