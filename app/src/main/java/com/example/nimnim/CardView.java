package com.example.nimnim;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nimnim.Utils.NoteDao;
import com.example.nimnim.Utils.NoteDatabase;
import com.example.nimnim.Utils.NotesEntity;

public class CardView extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private int index;
    private NoteDao noteDao;
    private TextView textViewCount;
    private Button buttonUpdate;
    private Button buttonAddNote;
    private View.OnClickListener onClickListener;

    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcard);
        onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonClick(v);
            }
        };
        buttonUpdate = findViewById(R.id.button_update);
        buttonAddNote = findViewById(R.id.button_addNote);
        buttonUpdate.setOnClickListener(onClickListener);
        buttonAddNote.setOnClickListener(onClickListener);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        textViewCount = findViewById(R.id.count);
        flag=true;

        noteDao = NoteDatabase.getInstance(this).getNoteDao();
        Intent intent = getIntent();
        Bundle bund = intent.getExtras();
        if (bund != null) {
            getSupportActionBar().setTitle("Edit Card");
            index = bund.getInt("Index");
            String description = bund.getString("Description");
            editTextTitle.setText(bund.getString("Title"));
            editTextDescription.setText(description);
            if(description.length()>0) textViewCount.setText(String.valueOf(description.length()));
            else textViewCount.setText("0");
        }
        else {
            textViewCount.setText("0");
            getSupportActionBar().setTitle("Add Card");
            Button button_update = findViewById(R.id.button_update);
            button_update.setVisibility(View.GONE);
        }


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



    public void buttonClick(View v) {

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        NotesEntity card = new NotesEntity();

        if(title.equals("")) card.setTitle("Empty title");
        else card.setTitle(title);

        if(description.equals("")) card.setContent("Empty Description");
        else card.setContent(description);

        if(flag)
        {
            flag=false;
            if (v.getTag() != null) {
                noteDao.insert(card);
                Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                noteDao.insert(card);
                card.setNote_id(index);
                noteDao.delete(card);

                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            }

        }
        finish();

    }

}
