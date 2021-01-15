package com.example.nimnim.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nimnim.Adapter.ImageAdapter;
import com.example.nimnim.MainActivity;
import com.example.nimnim.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Photos extends Fragment {
    private RecyclerView recyclerView2;
    private Toolbar toolbar;
    private AppCompatActivity activity;
    private ImageAdapter imageAdapter;
    private PopupMenu popup;
    private ImageButton dotButton;
    private List<Integer> ImageList;
    private int layoutSpan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        ImageList = new ArrayList<>();
        activity = (MainActivity) getActivity();
        assert activity != null;
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Photos");
        setHasOptionsMenu(true);
        ImageList.add(R.drawable.ic_banner_foreground);
        ImageList.add(R.drawable.ic_banner_foreground);
        ImageList.add(R.drawable.ic_banner_foreground);
        ImageList.add(R.drawable.ic_banner_foreground);
        ImageList.add(R.drawable.ic_banner_foreground);
        ImageList.add(R.drawable.ic_banner_foreground);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        layoutSpan =1;


        return view;
    }

    @Override
    public void onResume() {
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), layoutSpan));
        imageAdapter = new ImageAdapter(this);
        recyclerView2.setAdapter(imageAdapter);
        imageAdapter.setCards(ImageList);
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pop_menu, menu);
         MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                MenuItem spanMenu =  menu.findItem(R.id.spanOptions);
                Toast.makeText(getActivity(),"Expanded",Toast.LENGTH_SHORT).show();
                spanMenu.setEnabled(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                MenuItem spanMenu =  menu.findItem(R.id.spanOptions);
                spanMenu.setEnabled(true);
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
                // Toast like print
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
        onResume();
        return super.onOptionsItemSelected(item);

    }
}