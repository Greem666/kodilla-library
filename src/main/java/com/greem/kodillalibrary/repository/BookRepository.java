package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.book.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
