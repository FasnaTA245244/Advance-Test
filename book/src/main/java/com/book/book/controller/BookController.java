package com.book.book.controller;

import com.book.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getbook")
    public List<Book> getAllBook(){
        String url="http://localhost:8081/api/library/getbook";
        List output = restTemplate.getForObject(url,List.class);
        return output;
    }
    @PostMapping("/addbook")
    public Book addBook(@RequestBody Book book)
    {
        String url = "http://localhost:8081/api/library/createbook";
        ResponseEntity<Book> response = restTemplate.postForEntity(url, book, Book.class);
        return response.getBody();
    }
    @GetMapping("/get/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        String url = "http://localhost:8081/api/library/getbook/{bookId}";
        return restTemplate.getForObject(url, Book.class, bookId);
    }

    @PutMapping("/update/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        String url = "http://localhost:8081/api/library/updatebook/{bookId}";
        restTemplate.put(url, updatedBook, bookId,Book.class);
        return updatedBook;
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        String url = "http://localhost:8081/api/library/deletebook/{bookId}";
        restTemplate.delete(url, bookId,Book.class);
        return ResponseEntity.ok("Book deleted successfully");
    }
    
    
//    @GetMapping("/getbook")
//    public ResponseEntity<List<Book>> getAllBook() {
//    String url = "http://localhost:8081/api/library/getbook";
//    List<Book> output = restTemplate.getForObject(url, List.class);
//    return ResponseEntity.ok(output);	
//    }
//
//
//
//
//    @PostMapping("/addbook")
//    public Book addBook(@RequestBody Book book)
//    {
//    String url = "http://localhost:8081/api/library/createbook";
//    ResponseEntity<Book> response = restTemplate.postForEntity(url, book, Book.class);
//    return response.getBody();
//    }
//
//
//
//
//
//    @GetMapping("/get/{bookId}")
//    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
//    String url = "http://localhost:8081/api/library/getbook/{bookId}";
//    ResponseEntity<Book> responseEntity = restTemplate.getForEntity(url, Book.class, bookId);
//    return responseEntity;
//    }
//
//
//
//
//    @PutMapping("/update/{bookId}")
//    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
//    String url = "http://localhost:8081/api/library/updatebook/{bookId}";
//    HttpEntity<Book> requestEntity = new HttpEntity<>(updatedBook);
//    ResponseEntity<Book> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Book.class, bookId);
//    return responseEntity;
//    }
//
//
//
//
//    @DeleteMapping("/delete/{bookId}")
//    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
//    String url = "http://localhost:8081/api/library/deletebook/{bookId}";
//    restTemplate.delete(url, bookId,Book.class);
//    return ResponseEntity.ok("Book deleted successfully");
//    }




}
