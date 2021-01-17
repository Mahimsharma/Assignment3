package com.example.nimnim.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.nimnim.Adapter.ImageAdapter;
import com.example.nimnim.R;
import com.example.nimnim.Utils.ImageCache;
import com.example.nimnim.Utils.ImageDao;
import com.example.nimnim.Utils.ImageDatabase;
import com.example.nimnim.Utils.ImageEntity;

import com.example.nimnim.Utils.NetworkUtil;
import com.example.nimnim.ui.main.RequestHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Photos extends Fragment {
    private RecyclerView recyclerView2;
    private Toolbar toolbar;
    private AppCompatActivity activity;
    private ImageAdapter imageAdapter;
    private List<ImageEntity> ImageList;
    private RequestHandler requestHandler;
    private ProgressBar progressBar;
    private String current_query="dog";
    private int layoutSpan;

    private ImageDao imageDao;
    private List<ImageCache> imageCache;

    public static Photos newInstance() {
        Photos fragment = new Photos();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        progressBar=view.findViewById(R.id.progressBar);
        layoutSpan =1;
        activity = (AppCompatActivity) getActivity();
        assert activity != null;
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Photos");
        setHasOptionsMenu(true);
//        imageDao = ImageDatabase.getInstance(activity).getImageDao();
        ImageList=new ArrayList<>();
        requestHandler = RequestHandler.getInstance(getContext());
        imageAdapter = new ImageAdapter(Photos.this);
        if(NetworkUtil.getConnectivityStatus(activity)==0)
        {
            Toast.makeText(activity,"No connection",Toast.LENGTH_SHORT).show();
            int i=0;
            while (i<10)
            {
                ImageList.add(new ImageEntity("",""));
            }
//            imageCache = imageDao.getAll();
//            imageAdapter.setCache(imageCache);

        }
        else {
            ImageList = requestHandler.parseJson(current_query);
            imageAdapter.setCards(ImageList);
        }

        imageAdapter.setCards(ImageList);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setAdapter(imageAdapter);
        recyclerView2.setLayoutManager(new GridLayoutManager(activity,layoutSpan));
        recyclerView2.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int dy = scrollY - oldScrollY;
                if(dy > 0){ // only when scrolling up

                    final int visibleThreshold = 2;

                    GridLayoutManager layoutManager = (GridLayoutManager)recyclerView2.getLayoutManager();
                    int lastItem  = layoutManager.findLastCompletelyVisibleItemPosition();
                    int currentTotalCount = layoutManager.getItemCount();

                    if(currentTotalCount <= lastItem + visibleThreshold){
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onSearch(current_query);
                            }
                        },1000);

                    }
                }
            }
        });


        return view;
    }



    public void refresh() {

        imageAdapter.setCards(ImageList);
//        imageAdapter.notifyDataSetChanged();
        recyclerView2.setLayoutManager(new GridLayoutManager(activity, layoutSpan));

    }

    public void onSearch(String tag){
        if(NetworkUtil.getConnectivityStatus(activity)!=0)
        {
            ImageList=requestHandler.parseJson(tag);
            if(ImageList == null) Toast.makeText(getContext(),"You have reached the end",Toast.LENGTH_SHORT).show();
            else {
                if (current_query.equals(tag))
                    imageAdapter.addCards(ImageList);
                else {
                    current_query = tag;
                    imageAdapter.setCards(ImageList);
                }
            }
//        imageDao.delete(imageCache);
        refresh();
        }
        else{Toast.makeText(activity,"No connection",Toast.LENGTH_SHORT).show();};
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pop_menu, menu);
         MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                return true;
            }
        };

        MenuItem searchMenu =  menu.findItem(R.id.search);
        searchMenu.setOnActionExpandListener(onActionExpandListener);
        SearchView searchView =(SearchView) searchMenu.getActionView();
        searchView.setQueryHint("Search Query");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

               onSearch(query);
               Toast.makeText(getActivity(),"Searching for "+ query,Toast.LENGTH_SHORT).show();
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                searchMenu.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one :
                layoutSpan=1;
                break;
            case R.id.two :
                layoutSpan=2;
                break;
            case R.id.three :
                layoutSpan=3;
                break;
            case R.id.four :
                layoutSpan=4;
                break;
            default:
                break;

        }
        refresh();
        return true;

    }

    public void click() {
//        imageViewer.setVisibility(View.VISIBLE);
//        viewSearchImages.setVisibility(View.INVISIBLE);
//        imageAdapter.setCards(ImageList);
//
    }


}