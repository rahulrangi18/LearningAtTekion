package com.BookwithESDatabase.BookwithESDatabase.services;

import com.BookwithESDatabase.BookwithESDatabase.model.Book;

import java.util.List;

public interface BookServiceInterface {
    Book save(Book book);
    Book getById(String id);
    List<Book> getAll();
    void delete(String id);
    List<Book> searchByBookName(String bookName);
    List<Book> searchByAuthorName(String authorName);
}
