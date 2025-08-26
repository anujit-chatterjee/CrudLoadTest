package com.codingstrain.springcloud.sample.libraryapp.books.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingstrain.springcloud.sample.libraryapp.books.model.Book;
import com.codingstrain.springcloud.sample.libraryapp.books.repository.BookRepository;
import scopt.Opt;

@Service("bookService")
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book save(Book book) {
        return bookRepository.saveAndFlush(book);
    }


    public Optional<Book> updateByTitle(String title, Book book) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        if (optionalBook.isPresent()) {
            Book oldBook = optionalBook.get();
            oldBook.setTitle(book.getTitle());
            optionalBook = Optional.of(save(oldBook));
        } else {
            optionalBook = Optional.of(new Book());
        }
        return optionalBook;
    }

    public Optional<Book> deleteByTitle(String title) {
        return bookRepository.deleteByTitle(title);
    }


}
