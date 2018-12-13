package com.example.jacob.android_projectweek1_googlebooks;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BookshelfViewActivity extends AppCompatActivity {

    Context context;
//    private ArrayList<Book> books = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private BookshelfListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf_view);

        context = this;
        recyclerView = findViewById(R.id.recycler_view_bookshelf);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        int bookshelfId = getIntent().getIntExtra(Constants.VIEW_BOOKSHELF_KEY, -1);
        Bookshelf bookshelf;
        if (bookshelfId != -1) {
            bookshelf = BookshelfDbDao.readBookshelf(bookshelfId);
            listAdapter = new BookshelfListAdapter(bookshelf, this);
            recyclerView.setAdapter(listAdapter);
//            ArrayList<Book> shelfBooks = bookshelf.getBooks();
//            if (shelfBooks != null) {
//                books.addAll(bookshelf.getBooks());
//            }
        }
    }
}