package com.example.josand.dc_app.UI.Main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josand.dc_app.Model.Grupo;
import com.example.josand.dc_app.Model.Personaje;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.UI.Info.Group.InfoGroupActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by josan on 19/02/2018.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private ArrayList<Grupo> grupos;
    private Context mContext;

    public GroupAdapter(ArrayList<Grupo> grupos, Context mContext) {
        this.grupos = grupos;
        this.mContext = mContext;
    }

    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, int position) {
        try{
            final Grupo grupo = grupos.get(position) ;
            holder.name.setText(grupo.getName());
            Picasso.with(mContext).load(grupo.getImage())
                    .resize(50,100).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,InfoGroupActivity.class);
                    intent.putExtra("name",grupo.getName());
                    mContext.startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.itemName);
        }
    }
}
