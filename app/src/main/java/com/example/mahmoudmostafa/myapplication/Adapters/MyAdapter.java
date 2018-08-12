package com.example.mahmoudmostafa.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmoudmostafa.myapplication.R;
import com.example.mahmoudmostafa.myapplication.model.News;

import java.util.ArrayList;

/**
 * Created by Mahmoud Mostafa on 9/2/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<News> newses;

    public MyAdapter(Context context, ArrayList<News> newses) {
        this.context = context;
        this.newses = newses;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        TextView date;
        TextView name;
        TextView author;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Article_title);
            date = (TextView) itemView.findViewById(R.id.Date);
            name = (TextView) itemView.findViewById(R.id.sect_name);
            author = (TextView) itemView.findViewById(R.id.author);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final News model = newses.get(position);


        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDate());
        holder.name.setText(model.getSectionName());
        if (model.getAuthor() != null) {
            holder.author.setText(model.getAuthor());
        } else {
            holder.author.setText("author name not provided ");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getWebUrl()));
                context.startActivity(browserIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

}