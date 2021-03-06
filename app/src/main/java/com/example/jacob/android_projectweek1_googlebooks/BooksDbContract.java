package com.example.jacob.android_projectweek1_googlebooks;

import android.provider.BaseColumns;

public class BooksDbContract {
    static class BookEntry implements BaseColumns {
        static final String BOOK_TABLE_NAME = "books";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_AUTHOR = "author";
        static final String COLUMN_NAME_PUBLISHED_DATE = "published_date";
        static final String COLUMN_NAME_IMAGE_URL = "image_url";
        static final String COLUMN_NAME_REVIEW = "review";
        static final String COLUMN_NAME_HAS_BEEN_READ = "has_been_read";

        static final String BOOKSHELF_TABLE_NAME = "bookshelves";
        static final String COLUMN_NAME_BOOK_IDS = "book_ids";

        //TODO use a JOIN table.

        static final String SQL_BOOK_TABLE = "CREATE TABLE " + BOOK_TABLE_NAME +
                " (" +
                _ID + " STRING PRIMARY KEY, " +
                COLUMN_NAME_TITLE + " TEXT, " +
                COLUMN_NAME_AUTHOR + " TEXT, " +
                COLUMN_NAME_PUBLISHED_DATE + " TEXT, " +
                COLUMN_NAME_IMAGE_URL + " TEXT, " +
                COLUMN_NAME_REVIEW + " TEXT, " +
                COLUMN_NAME_HAS_BEEN_READ + " INTEGER);";

        static final String SQL_BOOKSHELF_TABLE = "CREATE TABLE " + BOOKSHELF_TABLE_NAME +
                " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_TITLE + " TEXT, " +
                COLUMN_NAME_BOOK_IDS + " TEXT);";

        static final String SQL_DELETE_BOOKS_TABLE = "DROP TABLE IF EXISTS " + BOOK_TABLE_NAME + ";";
        static final String SQL_DELETE_BOOKSHELF_TABLE = "DROP TABLE IF EXISTS " + BOOKSHELF_TABLE_NAME + ";";
    }
}
