package com.example.josand.dc_app.ui.Info.Group;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josand.dc_app.model.GrupoInfo;
import com.example.josand.dc_app.R;
import com.squareup.picasso.Picasso;

import me.gujun.android.taggroup.TagGroup;

public class InfoGroupActivity extends AppCompatActivity {

    private IInfoGroupActivity.Presenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private TextView base, firstAppearance;
    private TagGroup leader, members;
    private ImageView image;
    private GrupoInfo group;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_group);
        id = getIntent().getStringExtra("id");
        initViews();
        if(presenter == null){
            presenter = new InfoGroupPresenter(this,id);
        }
        group = getPresenter().getGroup();
        setUpData();

    }

    public void initViews(){
        group = new GrupoInfo();
        image = findViewById(R.id.detailGroupImageView);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayoutGroup);
        toolbar = findViewById(R.id.detailGroupToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        base = findViewById(R.id.baseGroupInfo);
        firstAppearance = findViewById(R.id.firstAppearanceGroupInfo);
        leader = findViewById(R.id.leaderTag);
        members = findViewById(R.id.membersTag);
    }

    public IInfoGroupActivity.Presenter getPresenter(){
        return presenter;
    }

    public void setUpData(){
        try{
            collapsingToolbarLayout.setTitle(group.getName());
            Picasso.with(getApplicationContext()).load(group.getProfile()).into(image);
            base.setText(group.getBase());
            leader.setTags(group.getLeader());
            members.setTags(group.getMembers());
            firstAppearance.setText(group.getFirstAppearance());
            getPresenter().onClickTag(leader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
