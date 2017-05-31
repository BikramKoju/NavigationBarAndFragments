package com.example.bikramkoju.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 3/31/2017.
 */

class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyHolder> {
    Context c;
    ArrayList<Module> savedData;
    LayoutInflater inflater;

    public MyCustomAdapter(FragmentActivity activity, ArrayList<Module> mydata) {
        c=activity;
        savedData=mydata;
        inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyCustomAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= inflater.inflate(R.layout.list_item,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyCustomAdapter.MyHolder holder, int position) {
        holder.TITLE.setText(savedData.get(position).title);
        holder.DATE.setText(savedData.get(position).date);

        Picasso.with(c).load(savedData.get(position).url).into(holder.imv);

    }

    @Override
    public int getItemCount() {
        return savedData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView imv;
        TextView TITLE,DATE;

        public MyHolder(View itemView) {
            super(itemView);
            imv=(ImageView)itemView.findViewById(R.id.imageview);
            TITLE=(TextView)itemView.findViewById(R.id.title);
            DATE=(TextView)itemView.findViewById(R.id.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(c,PlayerActivity.class);
                    Bundle b = new Bundle();
                    b.putString("videoid",savedData.get(getAdapterPosition()).videoid);
                    i.putExtras(b);
                    c.startActivity(i);


                }
            });

        }
    }
}


