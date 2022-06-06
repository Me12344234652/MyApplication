package com.example.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.UpdateBook;
import com.example.myapplication.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookHolder> {

    private final List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book book = bookList.get(position);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.place.setText(book.getPlace());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), com.example.myapplication.UpdateBook.class);
            int idBo = book.getId();
            intent.putExtra("key", idBo);
            Toast.makeText(view.getContext(), String.valueOf(idBo), Toast.LENGTH_SHORT).show();
            String nameBo = book.getName();
            intent.putExtra("keys", nameBo);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
