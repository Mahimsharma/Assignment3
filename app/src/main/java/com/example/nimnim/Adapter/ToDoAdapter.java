package com.example.nimnim.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nimnim.R;
import com.example.nimnim.Utils.NotesEntity;
import com.example.nimnim.fragments.Notes;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.CardViewHolder> {

    private List<NotesEntity> cardList;
    private Notes notes;
    private ImageButton imageButton;

    public ToDoAdapter(Notes notes)
    {
        this.notes = notes;

    }

    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
       final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_layout,parent,false);
       return new CardViewHolder(itemView);
    }
    public void onBindViewHolder(CardViewHolder holder, int position){

        NotesEntity item = cardList.get(position);
        holder.cardTitle.setText(item.getTitle());
        holder.cardDescription.setText(item.getContent());

    }
    public int getItemCount()
    {
        return cardList.size();
    }

    public void setCards(List<NotesEntity> cardList){
        this.cardList =  cardList;
        notifyDataSetChanged();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder
    {

        TextView cardTitle,cardDescription;
        CardViewHolder(View view)
        {
            super(view);
            cardTitle = view.findViewById(R.id.cardTitle);
            cardDescription = view.findViewById(R.id.cardDescription);
            cardDescription.getText().toString();
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId() != R.id.imageButton ) notes.click(view);
                    else notes.deleteCard(view);
                }
            };

            view.setOnClickListener(clickListener);
            imageButton.setOnClickListener(clickListener);

        }
    }
}
