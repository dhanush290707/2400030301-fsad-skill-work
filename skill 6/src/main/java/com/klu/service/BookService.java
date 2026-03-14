package com.klu.service;

import com.klu.exception.BookNotFoundException;
import com.klu.model.Book;
import com.klu.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // Additional methods based on what the controller needs
    public String getBookDetailsById(int id) {
        // Just keeping the mock behavior the controller had
        return "Book Details - ID: " + id + ", Title: Java Programming, Author: James Gosling";
    }
}
