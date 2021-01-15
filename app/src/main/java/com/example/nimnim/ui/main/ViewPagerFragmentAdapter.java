package com.example.nimnim.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nimnim.MainActivity;
import com.example.nimnim.fragments.Notes;
import com.example.nimnim.fragments.Photos;
import com.example.nimnim.fragments.frag2;
import com.example.nimnim.fragments.frag3;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {


    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Notes();
            case 1:
                return new Photos();
            case 2:
                return new frag3();
        }
        return new frag2();
    }

    @Override
    public int getItemCount() {
        return MainActivity.titles.length;
    }
}


