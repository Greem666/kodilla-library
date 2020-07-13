package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.rentlog.RentLog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RentLogRepository extends CrudRepository<RentLog, Long> {
    @Query(
            "SELECT count(*) = 0 " +
            "FROM RentLog rl JOIN rl.bookCopies bc " +
            "WHERE rl.libraryUser.id = :USER_ID AND bc.book.id = :BOOK_ID AND rl.returnDate IS NULL"
    )
    boolean userHasNoActiveRentOfBook(@Param("BOOK_ID") long bookId, @Param("USER_ID") long userId);

    @Query(
            "SELECT rl.id " +
            "FROM RentLog rl JOIN rl.bookCopies bc " +
            "WHERE bc.id = :BOOK_COPY_ID AND rl.returnDate IS NULL"
    )
    long findOpenRentLogIdWithBookCopyId(@Param("BOOK_COPY_ID") long bookCopyId);

    @Query(
            "FROM RentLog rl " +
            "JOIN rl.bookCopies bc " +
            "JOIN bc.book bk " +
            "WHERE rl.id = :RENT_LOG_ID"
    )
    RentLog findRentLogWithAssociationsById(@Param("RENT_LOG_ID") long rentLogId);

    @EntityGraph(attributePaths = {"bookCopies", "bookCopies.book", "libraryUser"})
    RentLog findById(long id);
}
