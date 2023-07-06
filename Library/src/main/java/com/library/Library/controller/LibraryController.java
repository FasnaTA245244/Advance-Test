package com.library.Library.controller;

import com.library.Library.entity.Book;
import com.library.Library.repository.BookRepository;
import com.library.Library.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createbook")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }
    @GetMapping("/getbook")
    public ResponseEntity<List<Book>> getAllBook(){
        return new ResponseEntity<List<Book>>(bookRepository.findAll(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getbook/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId){
        Optional<Book> op= bookRepository.findById(bookId);
        if(op.isPresent()){
            return new ResponseEntity<Book>(op.get(),HttpStatus.FOUND);
        }
        else
            return  ResponseEntity.notFound().build();

    }

    @PutMapping("/updatebook/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book , @PathVariable Long bookId){
        Optional<Book> op= bookRepository.findById(bookId);

        if(op.isPresent()){
            Book bookop = op.get();
            bookop.setName(book.getName());
            bookop.setAuthor(book.getAuthor());
            return new ResponseEntity<Book>(bookRepository.save(bookop),HttpStatus.CREATED);

        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletebook/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId){
        Optional<Book> op= bookRepository.findById(bookId);
        if(op.isPresent())
        {
            bookRepository.deleteById(bookId);
            return new ResponseEntity<String>("deleted successfully",HttpStatus.FOUND);
        }
        else
            return ResponseEntity.notFound().build();
    }
}
