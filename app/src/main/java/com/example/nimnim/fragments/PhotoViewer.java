package com.example.nimnim.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nimnim.Adapter.ImageAdapter;
import com.example.nimnim.R;

import java.util.List;

public class PhotoViewer extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ImageAdapter adapter;
    private List<Integer> imageList;
    private Photos photos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.fragment_photo_viewer);
       Intent intent = getIntent();
       Bundle bundle = intent.getExtras();
       imageList = (List<Integer>) bundle.getSerializable("imageList");
        viewPager = findViewById(R.id.viewPagerImages);
//        adapter = new ImageAdapter();
 //       viewPager.setAdapter(adapter);
   }

    @Override
    public void onResume() {
        super.onResume();
   //     adapter.setCards(imageList);
    }
}
