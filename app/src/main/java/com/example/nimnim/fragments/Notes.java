package com.example.nimnim.fragments;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nimnim.Adapter.ToDoAdapter;
import com.example.nimnim.CardView;
import com.example.nimnim.R;
import com.example.nimnim.Utils.NoteDao;
import com.example.nimnim.Utils.NoteDatabase;
import com.example.nimnim.Utils.NotesEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class Notes extends Fragment {
    private RecyclerView recyclerView;
    private ToDoAdapter cardAdapter;
    private List<NotesEntity> cardList;
    boolean flag;
    private NoteDao noteDao;
    private FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notes, container, false);
        fab = view.findViewById(R.id.fab);
        noteDao =  NoteDatabase.getInstance(getContext()).getNoteDao();
       recyclerView = view.findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       cardAdapter = new ToDoAdapter(this);
       recyclerView.setAdapter(cardAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click(v);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        flag=true;
        cardList = noteDao.getAll();
        Collections.reverse(cardList);
        cardAdapter.setCards(cardList);

    }

    public void click(View v) {

            Intent intent = new Intent(getActivity() , CardView.class);
            if (v.getId() != R.id.fab) {
                NotesEntity item = cardList.get(recyclerView.getChildLayoutPosition(v));
                intent.putExtra("Title", item.getTitle());
                intent.putExtra("Description", item.getContent());
                intent.putExtra("Index", item.getNote_id());
            }
            startActivity(intent);
    }


    public void deleteCard(View v) {

            int position = recyclerView.getChildLayoutPosition(v);
            NotesEntity card = cardList.get(position);
            noteDao.delete(card);
            cardList.remove(position);
            Toast.makeText(getContext(),"Note Deleted!",Toast.LENGTH_SHORT).show();
            cardAdapter.setCards(cardList);

    }

}
