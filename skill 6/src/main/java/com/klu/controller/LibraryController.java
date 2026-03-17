package com.klu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.klu.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    private List<Book> booksList = new ArrayList<>();

    // 1. /welcome - returns a welcome message
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Online Library System!";
    }

    // 2. /count - returns an integer representing total books
    @GetMapping("/count")
    public int count() {
        return 5;
    }

    // 3. /price - returns a sample book price as double
    @GetMapping("/price")
    public double price() {
        return 499.99;
    }

    // 4. /books - returns a list of book titles
    @GetMapping("/books")
    public List<String> books() {
        return Arrays.asList("Java: The Complete Reference", "Spring in Action", "Head First Design Patterns", "Clean Code", "Effective Java");
    }

    // 5. /books/{id} - returns book details using @PathVariable
    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable int id) {
        for (Book book : booksList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // 6. /search - accepts a request parameter (title) and returns a confirmation message
    @GetMapping("/search")
    public String search(@RequestParam String title) {
        return "Searching for book with title: " + title;
    }

    // 7. /author/{name} - returns a formatted message with the author's name
    @GetMapping("/author/{name}")
    public String author(@PathVariable String name) {
        return "Books by Author: " + name;
    }

    // 8. /addbook - accepts a Book object from request body and adds it to the list
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        booksList.add(book);
        return "Book added successfully";
    }

    // 9. /viewbooks - returns all added Book objects
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return booksList;
    }
}
