package com.example.josand.dc_app.UI.Info.Group;

import android.content.Context;
import android.content.Intent;

import com.example.josand.dc_app.Model.Character;
import com.example.josand.dc_app.Model.Group;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.UI.Info.Character.InfoCharacterActivity;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by josan on 13/02/2018.
 */

public class InfoGroupPresenter {
    private Context mContext;
    private IInfoGroupActivity iInfoGroupActivity;
    private Group group;
    private List<String> members;

    public InfoGroupPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public Group getData(){
        members = new ArrayList<>();
        members.add("Superman");
        members.add("Batman");
        members.add("Wonder Woman");
        group = new Group(members,"123","https://images3.alphacoders.com/802/802240.jpg",
                "Justice League","WatchOver","Superman",
                "The Brave and The Bold #28 (1960)");
        return group;
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
