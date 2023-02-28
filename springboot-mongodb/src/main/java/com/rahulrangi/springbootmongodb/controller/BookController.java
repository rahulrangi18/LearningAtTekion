package com.rahulrangi.springbootmongodb.controller;

import com.rahulrangi.springbootmongodb.model.Book;
import com.rahulrangi.springbootmongodb.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

     @Autowired
     private BookRepository bookRepository;

     @PostMapping("/addBook")
     public String saveBook(@RequestBody Book book){
         bookRepository.save(book);
         return "Added book with id: "+book.getId();
     }
     @GetMapping("/getAllBooks")
     public List<Book> getBooks(){
         return bookRepository.findAll();
     }
     @GetMapping("/getBook/{id}")
     public Optional<Book> getBook(@PathVariable int id){
         return bookRepository.findById(id);
     }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
        return "Book deleted with id: "+id;
    }

}
