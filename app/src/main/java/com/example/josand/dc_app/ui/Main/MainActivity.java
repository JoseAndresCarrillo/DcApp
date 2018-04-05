package com.example.josand.dc_app.ui.Main;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.josand.dc_app.R;
import com.example.josand.dc_app.ui.Main.Adapter.ViewPageAdapter;
import com.example.josand.dc_app.ui.Main.Character.CharacterFragment;
import com.example.josand.dc_app.ui.Main.Group.GroupFragment;
import com.example.josand.dc_app.ui.Main.Home.HomeFragment;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private MainPresenter mainPresenter;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(mainPresenter ==null){
            mainPresenter = new MainPresenter(getApplicationContext());
        }
        initView();
        setupViewPager();
        setupBottomNavigationView();
    }

    private void setupViewPager() {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        CharacterFragment characterFragment = new CharacterFragment();
        GroupFragment groupFragment = new GroupFragment();
        adapter.addFragment(characterFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(groupFragment);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuItem != null){
                    menuItem.setChecked(false);
                }else {
                    bottomNavigationView.getMenu().getItem(2).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupBottomNavigationView() {
        if(bottomNavigationView != null){
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectFragment(item);
                    return false;
                }
            });
        }
    }

    public void selectFragment (MenuItem item){
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.home:
                viewPager.setCurrentItem(1);
                break;
            case R.id.character:
                viewPager.setCurrentItem(0);
                break;
            case R.id.group:
                viewPager.setCurrentItem(2);
                break;
        }

    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }

    public MainPresenter getMainPresenter(){
        return mainPresenter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(isFragmentVisible(R.id.home)){
            super.onBackPressed();
        }else{
            if(getSupportFragmentManager().getBackStackEntryCount()!=0){
                getSupportFragmentManager().popBackStack();
            }else {
                viewPager.setCurrentItem(1);
            }
        }
    }

    private boolean isFragmentVisible(int id) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(id);
        return fragment != null && fragment.isVisible();
    }

    //Este método se llama cuando el sistema está a punto de poner la Actividad en background
    @Override
    protected void onPause() {
        super.onPause();
        getMainPresenter().onViewDetached();
    }

    //Este método se llama cuando la Actividad va a empezar a interactuar con el usuario después de estar en un estado de pausa.
    @Override
    protected void onResume() {
        super.onResume();
        getMainPresenter().onViewAttached(MainActivity.this);
    }
}
