package com.example.josand.dc_app.UI.Info.Group;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josand.dc_app.Model.Group;
import com.example.josand.dc_app.Model.GrupoInfo;
import com.example.josand.dc_app.R;
import com.squareup.picasso.Picasso;

import me.gujun.android.taggroup.TagGroup;

public class InfoGroupActivity extends AppCompatActivity {

    private InfoGroupPresenter presenter;
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
            presenter = new InfoGroupPresenter(getApplicationContext(),id);
        }
        group = getPresenter().getGroup();
        setUpData();

    }

    public void initViews(){
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

    public InfoGroupPresenter getPresenter(){
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
