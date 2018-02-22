package com.example.josand.dc_app.UI.Main;

import android.content.Context;

/**
 * Created by josan on 9/02/2018.
 */

public class MainPresenter {

    private Context mContext;
    private IMainActivity iMainActivity;

    public MainPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void onViewAttached(IMainActivity iMainActivity){
        this.iMainActivity = iMainActivity;
    }

    public void onViewDetached(){
        iMainActivity = null;
    }


}
