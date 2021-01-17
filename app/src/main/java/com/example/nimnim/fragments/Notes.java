package com.example.nimnim.fragments;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nimnim.Adapter.ToDoAdapter;
import com.example.nimnim.R;
import com.example.nimnim.Utils.NoteDao;
import com.example.nimnim.Utils.NoteDatabase;
import com.example.nimnim.Utils.NotesEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Notes extends Fragment {
    //AllCards fields
    private RecyclerView recyclerView;
    private ToDoAdapter cardAdapter;
    private List<NotesEntity> cardList;
    private NoteDao noteDao;
    private View allCards, viewCardID;
    private ImageButton upButton;
    private FloatingActionButton fab;
    private ActionBar toolbar;

    //ViewCard fields
    private EditText editTextTitle;
    private EditText editTextDescription;
    private TextView textViewCount;
    private Button buttonUpdate;
    private Button buttonAddNote;
    private View.OnClickListener onClickListener;
    private View.OnClickListener onClickListener2;
    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notes, container, false);
        allCards=view.findViewById(R.id.allCards);
        viewCardID=view.findViewById(R.id.viewCardID);
        upButton = view.findViewById(R.id.upButton);
        viewCardID.setVisibility(View.GONE);
        upButton.setVisibility(View.GONE);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick(v);
            }
        };

        onClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
        setAllCards(view);
        setViewCards(view);
        return view;
    }
    private void setAllCards(View view)
    {
        AppCompatActivity activity =(AppCompatActivity)getActivity();
        assert activity != null;
        activity.setSupportActionBar(view.findViewById(R.id.toolbar));
        toolbar=activity.getSupportActionBar();
        Objects.requireNonNull(toolbar).setTitle("Tasks");

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
    }
    private void setViewCards(View view){

        buttonUpdate = view.findViewById(R.id.button_update);
        buttonAddNote = view.findViewById(R.id.button_addNote);
        buttonUpdate.setOnClickListener(onClickListener);
        buttonAddNote.setOnClickListener(onClickListener);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        textViewCount = view.findViewById(R.id.count);

        TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textViewCount.setText(String.valueOf(s.length()));
            }

            public void afterTextChanged(Editable s) {
            }
        };

        editTextDescription.addTextChangedListener(mTextEditorWatcher);
    }
    public void onBackPressed()
    {
        viewCardID.setVisibility(View.GONE);
        allCards.setVisibility(View.VISIBLE);
        toolbar.setTitle("Tasks");
    }

    @Override
    public void onResume() {
        super.onResume();
        cardList = noteDao.getAll();
        Collections.reverse(cardList);
        cardAdapter.setCards(cardList);

    }

    public void click(View view) {

    allCards.setVisibility(View.GONE);
    viewCardID.setVisibility(View.VISIBLE);
    upButton.setVisibility(View.VISIBLE);
    upButton.setOnClickListener(onClickListener2);

        if (view.getId() != R.id.fab) {
            position = recyclerView.getChildLayoutPosition(view);
            NotesEntity card = cardList.get(position);
            toolbar.setTitle("Edit Card");
            String description = card.getContent();
            editTextTitle.setText(card.getTitle());
            editTextDescription.setText(description);
            textViewCount.setText(String.valueOf(description.length()));
        }
        else{
            toolbar.setTitle("Add Card");
            buttonUpdate.setVisibility(View.GONE);
            editTextTitle.setText("");
            editTextDescription.setText("");
            textViewCount.setText("0");
        }
    }

    public void deleteCard(View v) {

            int position = recyclerView.getChildLayoutPosition(v);
            NotesEntity card = cardList.get(position);
            noteDao.delete(card);
            cardList.remove(position);
            Toast.makeText(getContext(),"Note Deleted!",Toast.LENGTH_SHORT).show();
            cardAdapter.setCards(cardList);

    }

    public void buttonClick(View v) {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        NotesEntity card = new NotesEntity();

        if(title.equals("")) card.setTitle("Empty title");
        else card.setTitle(title);

        if(description.equals("")) card.setContent("Empty Description");
        else card.setContent(description);


            if (v.getTag() != null) {
                noteDao.insert(card);
                Toast.makeText(getActivity(), "Note added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                noteDao.insert(card);
                card.setNote_id(position);
                noteDao.delete(card);
                Toast.makeText(getActivity(), "Note updated", Toast.LENGTH_SHORT).show();
            }

        onResume();
        onBackPressed();
    }

}
