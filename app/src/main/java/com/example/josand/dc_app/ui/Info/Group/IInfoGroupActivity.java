package com.example.josand.dc_app.ui.Info.Group;

import com.example.josand.dc_app.model.GrupoInfo;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by josan on 13/02/2018.
 */

public class IInfoGroupActivity {
    //implementar interface para setear datos
    public interface View{

    }
    public interface Presenter{
        GrupoInfo getGroup();
        void onClickTag(TagGroup tagGroup);
    }
}
