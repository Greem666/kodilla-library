package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;

public interface CopyRepository extends CrudRepository<BookCopy, Long> {
}
