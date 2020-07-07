package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
