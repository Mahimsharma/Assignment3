package com.example.nimnim.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nimnim.R;
import com.example.nimnim.Utils.ImageCache;
import com.example.nimnim.Utils.ImageDao;
import com.example.nimnim.Utils.ImageDatabase;
import com.example.nimnim.Utils.ImageEntity;
import com.example.nimnim.fragments.Photos;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


        private List<ImageEntity> imageList;
        private Photos photos;
        Context parentContext;
        private ImageDao imageDao;
        private List<ImageCache> imageCache;
        // data is passed into the constructor
        public ImageAdapter(Photos photos) {
            this.photos=photos;
            parentContext = photos.getContext();
           // imageDao = ImageDatabase.getInstance(parentContext).getImageDao();
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

            if(imageList!=null){
            ImageEntity currentImage = imageList.get(position);
            Picasso.with(parentContext).load(currentImage.getImageUrl()).fit().centerInside().into(holder.imageView);
//            Picasso.with(parentContext)
//                    .load(currentImage.getImageUrl())
//                    .into(new Target() {
//                        @Override
//                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                            ImageCache image = new ImageCache();
//                            image.setImage(bitmap);
//                            imageDao.insert(image);
//                        }
//
//                        @Override
//                        public void onBitmapFailed(Drawable errorDrawable) {
//
//                        }
//
//                        @Override
//                        public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                        }
//                    });
                   }
            else{
//                holder.imageView.setImageBitmap(imageCache.get(position).getImage());
            }

        }

        public void setCards(List<ImageEntity> imageList){
            this.imageList = imageList;
            notifyDataSetChanged();
        }
        public void addCards(List<ImageEntity> imageList){
        this.imageList.addAll( imageList);
        notifyDataSetChanged();
        }
//        public void setCache(List<ImageCache> imageList){
//        this.imageCache =  imageCache;
//        notifyDataSetChanged();
//    }

    // total number of cells
        @Override
        public int getItemCount() {
            return imageList.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView imageView;
            ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(parentContext,"Clicked -->",Toast.LENGTH_SHORT);
                        photos.click();
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