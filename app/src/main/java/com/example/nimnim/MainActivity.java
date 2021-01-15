package com.example.nimnim;

import android.os.Bundle;

import com.example.nimnim.ui.main.ViewPagerFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerFragmentAdapter adapter;
    public static String[] titles = new String[]{"Notes", "Photos", "Profile"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerFragmentAdapter(this);
        viewPager.setAdapter(adapter);
        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();
    }

}