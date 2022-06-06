package com.example.myapplication;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.adapter.BookAdapter;
import com.example.myapplication.model.Book;
import com.example.myapplication.retrofit.BookApi;
import com.example.myapplication.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Objects;

public class List extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Back to main screen");
        recyclerView = findViewById(R.id.recyclerview_bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton = findViewById(R.id.bookList_FabButton);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddBook.class);
            startActivity(intent);
        });
        loadBooks();
    }

    private void loadBooks() {

        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);
        bookApi.getAllBooks()
                .enqueue(new Callback<java.util.List<Book>>() {
                    @Override
                    public void onResponse(Call<java.util.List<Book>> call, Response<java.util.List<Book>> response) {
                        booksListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<java.util.List<Book>> call, Throwable t) {
                        Toast.makeText(List.this, "Failed to load Books", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void booksListView(java.util.List<Book> bookList) {
        BookAdapter bookAdapter = new BookAdapter(bookList);
        recyclerView.setAdapter(bookAdapter);

    }
}