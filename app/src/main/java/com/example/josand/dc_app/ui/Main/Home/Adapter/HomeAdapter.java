package com.example.josand.dc_app.ui.Main.Home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josand.dc_app.model.Character;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.ui.Info.Character.InfoCharacterActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by josan on 9/02/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<Character> characters;
    private Context mContext;

    public HomeAdapter(ArrayList<Character> characters, Context mContext) {
        this.characters = characters;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.homeitems,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            final Character character = characters.get(position);
            holder.name.setText(character.getName());
            holder.alias.setText(character.getAlias());
            Picasso.with(mContext).load(character.getUrlImage())
                    .resize(50,100).into(holder.photo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView name, alias;

        private ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.homeImage);
            name = itemView.findViewById(R.id.homeName);
            alias = itemView.findViewById(R.id.homeDescrip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,InfoCharacterActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
