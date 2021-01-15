package com.example.nimnim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nimnim.R;
import com.example.nimnim.Utils.NotesEntity;
import com.example.nimnim.fragments.Photos;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


        private List<Integer> imageList;
        private Photos photos;

        // data is passed into the constructor
        public ImageAdapter(Photos photos) {
            this.photos = photos;
        }

        // inflates the cell layout from xml when needed
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_photo_layout,parent,false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each cell
        @Override
        public void onBindViewHolder( ViewHolder holder, int position) {
            holder.gridIcon.setImageResource(imageList.get(position));
        }

        public void setCards(List<Integer> imageList){
            this.imageList =  imageList;
            notifyDataSetChanged();
        }
        // total number of cells
        @Override
        public int getItemCount() {
            return imageList.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView gridIcon;

            ViewHolder(View itemView) {
                super(itemView);
                gridIcon = itemView.findViewById(R.id.imageView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }



        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
}