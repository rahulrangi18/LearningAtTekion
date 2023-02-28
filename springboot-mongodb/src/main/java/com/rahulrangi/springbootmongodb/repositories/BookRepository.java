package com.rahulrangi.springbootmongodb.repositories;

import com.rahulrangi.springbootmongodb.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,Integer> {
}
