package com.example.cardviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.Viewholder> {


    // View holder class for initializing TextViews and ImageViews
    public class Viewholder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;
        public TextView titleTextView, countTextView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            thumbnailImageView = itemView.findViewById(R.id.thumbnail);
            titleTextView = itemView.findViewById(R.id.title);
            countTextView = itemView.findViewById(R.id.count);
        }
    }



    // AppsAdapter class extends RecyclerView.Adapter<AppsAdapter.Viewholder>
    private Context context;
    private ArrayList<AppsModel> appsModelArrayList;


    // Constructor
    public AppsAdapter(Context context, ArrayList<AppsModel> appsModelArrayList) {
        this.context = context;
        this.appsModelArrayList = appsModelArrayList;
    }


    @NonNull
    @Override
    public AppsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_app, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        AppsModel model = appsModelArrayList.get(position);

        //holder.thumbnailImageView.setImageResource(model.getThumbnail());
        holder.titleTextView.setText(model.getName());
        holder.countTextView.setText(model.getNumOfDownloads() + " Users");

        // Displaying image using Glide Library
        Glide.with(context).load(model.getThumbnail()).into(holder.thumbnailImageView);

        // ALL CLICKLISTENER METHODS GO HERE, NOT IN MAIN ACTIVITY
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display a toast message to the user
                // Display the title of the card clicked
                Toast.makeText(context, "You clicked: " + model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // used for showing number of card items in recycler view.
        return appsModelArrayList.size();
    }




}
