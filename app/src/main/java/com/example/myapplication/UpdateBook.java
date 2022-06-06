package com.example.myapplication;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.model.Book;
import com.example.myapplication.retrofit.BookApi;
import com.example.myapplication.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateBook extends AppCompatActivity {

    public int idBo;
    public String nameBo;
    public TextView textView_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Back to main book list");
        initializeComponents();

    }


    private void initializeComponents() {
        TextInputEditText inputEditName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditAuthor = findViewById(R.id.form_textFieldAuthor);
        TextInputEditText inputEditPlace = findViewById(R.id.form_textFieldPlace);
        MaterialButton updateButton = findViewById(R.id.buttonUpdate);

        idBo = getIntent().getIntExtra("key", idBo);
        nameBo = getIntent().getStringExtra("keys");

        textView_Name = findViewById(R.id.textView_Name);
        textView_Name.setText(nameBo);

        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);
        updateButton.setOnClickListener(v -> {
            String name = Objects.requireNonNull(inputEditName.getText()).toString();
            String author = Objects.requireNonNull(inputEditAuthor.getText()).toString();
            String place = Objects.requireNonNull(inputEditPlace.getText()).toString();

            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setPlace(place);

            bookApi.updateBook(idBo, book)
                    .enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            Toast.makeText(UpdateBook.this, "Update succesfull", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Toast.makeText(UpdateBook.this, "Update failed", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                        }
                    });
        });
        MaterialButton deleteButton = findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(view -> {
            bookApi.deleteBook(idBo)
                    .enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            Toast.makeText(UpdateBook.this, "Delete succesfull", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Toast.makeText(UpdateBook.this, "Delete succesfull", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                        }
                    });
        });
    }
}
