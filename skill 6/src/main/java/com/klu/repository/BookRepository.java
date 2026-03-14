package com.klu.repository;

import com.klu.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final List<Book> bookList = new ArrayList<>();

    public Book save(Book book) {
        bookList.add(book);
        return book;
    }

    public List<Book> findAll() {
        return bookList;
    }

    public Optional<Book> findById(int id) {
        return bookList.stream().filter(book -> book.getId() == id).findFirst();
    }
}
