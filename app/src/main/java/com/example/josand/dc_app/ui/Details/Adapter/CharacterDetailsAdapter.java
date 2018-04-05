package com.example.josand.dc_app.ui.Details.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josand.dc_app.model.Character;
import com.example.josand.dc_app.model.Personaje;
import com.example.josand.dc_app.R;
import com.example.josand.dc_app.ui.Info.Character.InfoCharacterActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by josan on 12/02/2018.
 */

public class CharacterDetailsAdapter extends BaseAdapter implements Filterable{

    private CustomFilter customFilter;
    private ArrayList<Personaje> list,filterList;
    private Context mContext;

    public CharacterDetailsAdapter(ArrayList<Personaje> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        this.filterList = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_items,viewGroup,false);
        }
        ImageView img=view.findViewById(R.id.characterImageView);
        TextView name=view.findViewById(R.id.characterTextView);

        Personaje character = list.get(i);

        Picasso.with(mContext).load(character.getImage()).resize(50,100).into(img);
        name.setText(character.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, InfoCharacterActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        return view;
    }

    @Override
    public Filter getFilter() {
        if(customFilter == null){
            customFilter = new CustomFilter();
        }

        return customFilter;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if(constraint != null || constraint.length()>0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<Character> filters = new ArrayList<>();
                for (int i=0;i<filterList.size();i++){
                    if(filterList.get(i).getName().contains(constraint)){
                        Character item = new Character(filterList.get(i).getName(),filterList.get(i).getImage());
                        filters.add(item);
                    }
                }
                filterResults.count=filters.size();
                filterResults.values=filters;
            }else {
                filterResults.count=filterList.size();
                filterResults.values=filterList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list = (ArrayList<Personaje>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
