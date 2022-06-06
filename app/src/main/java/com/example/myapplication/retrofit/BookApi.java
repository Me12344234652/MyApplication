package com.example.myapplication.retrofit;

import com.example.myapplication.model.Book;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BookApi {
    @GET("/Books")
    Call<List<Book>> getAllBooks();

    @POST("/addBook")
    Call<Book> addBook(@Body Book book);

    @PUT("/update/{id}")
    Call<Book> updateBook(@Path("id") int id, @Body Book book);

    @DELETE("/delete/{id}")
    Call<Book> deleteBook(@Path("id") int id);

}
