package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
//    @Query
//    List<BookCopy> findAllBookCopiesByTitle(@Param("TITLE") String title);
}
