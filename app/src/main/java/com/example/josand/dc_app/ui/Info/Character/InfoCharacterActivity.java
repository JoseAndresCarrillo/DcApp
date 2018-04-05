package com.example.josand.dc_app.ui.Info.Character;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josand.dc_app.model.PersonajeInfo;
import com.example.josand.dc_app.R;
import com.squareup.picasso.Picasso;

public class InfoCharacterActivity extends AppCompatActivity {

    private InfoCharacterPresenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private IInfoCharacterActivity iInfoCharacterActivity;
    private Toolbar toolbar;
    private TextView realName,quote,alias,base,powers,occupation, firstAppareance;
    private ImageView imgProf;
    private PersonajeInfo character;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_character);
        id = getIntent().getStringExtra("id");
        initView();
        if(presenter == null){
            presenter = new InfoCharacterPresenter(this,id);
        }
        character = getPresenter().setUpPersonaje();
        setUpData();
    }

    public void initView(){
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayoutCharacter);
        toolbar = findViewById(R.id.detailCharacterToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgProf= findViewById(R.id.detailCharacterImageView);
        quote = findViewById(R.id.detailQuote);
        realName = findViewById(R.id.nameCharacterDetails);
        alias = findViewById(R.id.aliasCharacterDetails);
        base = findViewById(R.id.baseCharacterDetails);
        powers = findViewById(R.id.powerCharacterDetails);
        occupation = findViewById(R.id.occupationCharacterDetails);
        firstAppareance = findViewById(R.id.firstAppearanceCharacterDetails);
    }

    public InfoCharacterPresenter getPresenter(){
        return presenter;
    }

    public void setUpData(){
        try{
            collapsingToolbarLayout.setTitle(character.getName());
            Picasso.with(getApplicationContext()).load(character.getProfile()).into(imgProf);
            quote.setText(character.getQuote());
            realName.setText(character.getRealName());
            alias.setText(character.getAlias());
            base.setText(character.getBase());
            powers.setText(character.getPowers());
            occupation.setText(character.getOccupation());
            firstAppareance.setText(character.getFirstAppearance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onViewDettached();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onViewAttached(iInfoCharacterActivity);
    }
}
