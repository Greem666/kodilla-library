package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Query(
            "UPDATE BookCopy bc SET bc.rentStatus = :STATUS WHERE bc.id = :ID"
    )
    @Modifying
    int updateRentStatus(@Param("ID") long id, @Param("STATUS") RentStatus rentStatus);

    @Query(
            "SELECT count(*) FROM BookCopy bc JOIN bc.book WHERE bc.book.title = :TITLE AND bc.rentStatus = 'AVAILABLE'"
    )
    int countAvailableBookCopies(@Param("TITLE") String title);
}
