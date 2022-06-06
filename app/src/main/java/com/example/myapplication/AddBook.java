package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.model.Book;
import com.example.myapplication.retrofit.BookApi;
import com.example.myapplication.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Back to main book list");
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditAuthor = findViewById(R.id.form_textFieldAuthor);
        TextInputEditText inputEditPlace = findViewById(R.id.form_textFieldPlace);
        MaterialButton buttonSave = findViewById(R.id.buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);
        buttonSave.setOnClickListener(v -> {
            String name = Objects.requireNonNull(inputEditName.getText()).toString();
            String author = Objects.requireNonNull(inputEditAuthor.getText()).toString();
            String place = Objects.requireNonNull(inputEditPlace.getText()).toString();

            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setPlace(place);

            bookApi.addBook(book)
                    .enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            Toast.makeText(AddBook.this, "Save succesfull", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Toast.makeText(AddBook.this, "Save failed", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                        }
                    });
        });
    }
}
