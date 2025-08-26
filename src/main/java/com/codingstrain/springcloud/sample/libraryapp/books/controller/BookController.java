package com.codingstrain.springcloud.sample.libraryapp.books.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codingstrain.springcloud.sample.libraryapp.books.model.Book;
import com.codingstrain.springcloud.sample.libraryapp.books.service.BookService;

@RestController
@RequestMapping("/library")
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @PostMapping(value = "/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> createPerson(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @GetMapping(value = "/findBookByTitle", params = { "title" })
    public Optional<Book> findByTitle(@RequestParam String title) {
        return bookService.findByTitle(title);
    }

    @PutMapping(value = "/updateBookByTitle", params = { "title" })
    public Optional<Book> updateByTitle(@RequestParam String title, @RequestBody Book book) {
        return bookService.updateByTitle(title, book);
    }

    @DeleteMapping(value = "/deleteBookByTitle", params = { "title" })
    public Optional<Book> deleteByTitle(@RequestParam String title) {
        return bookService.deleteByTitle(title);
    }



}