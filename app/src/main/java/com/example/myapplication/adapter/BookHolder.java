package com.example.myapplication.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

public class BookHolder extends RecyclerView.ViewHolder {

    TextView name, author, place;

    public BookHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.bookListText_Name);
        author = itemView.findViewById(R.id.bookListText_Author);
        place = itemView.findViewById(R.id.bookListText_Place);

    }
}
