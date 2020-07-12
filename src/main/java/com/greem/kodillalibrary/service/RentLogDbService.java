package com.greem.kodillalibrary.service;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.exceptions.*;
import com.greem.kodillalibrary.repository.BookCopyRepository;
import com.greem.kodillalibrary.repository.BookRepository;
import com.greem.kodillalibrary.repository.LibraryUserRepository;
import com.greem.kodillalibrary.repository.RentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class RentLogDbService {
    private final RentLogRepository rentLogRepository;

    private final BookRepository bookRepository;

    private final BookCopyDbService bookCopyDbService;
    private final BookCopyRepository bookCopyRepository;

    private final LibraryUserRepository libraryUserRepository;

    public RentLog rentBooks(List<Long> bookIds, long userId) {
        LibraryUser user = libraryUserRepository.findById(userId).orElseThrow(() -> new LibraryUserNotFound("User by id:" + userId + " was not found."));

        List<BookCopy> bookCopiesToBeRented = new ArrayList<>();

        for (long bookId: bookIds) {
            try {
                bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book by id:" + bookId + " was not found."));

                List<BookCopy> availableBookCopies = bookCopyRepository.findAvailableBookCopies(bookId);
                if (availableBookCopies.size() > 0) {
                    if (userHasNoActiveRentOfBook(bookId, userId)) {
                        BookCopy checkedOutBookCopy = availableBookCopies.get(0);

                        checkedOutBookCopy.setRentStatus(RentStatus.HIRED);
                        bookCopyDbService.updateBookCopyRentStatus(checkedOutBookCopy);

                        bookCopiesToBeRented.add(checkedOutBookCopy);
                    } else {
                        throw new BookCopyAlreadyRentedByUserException("User id:" + userId + " already has an active rent of book id:" + bookId + ".");
                    }
                } else {
                    throw new NoAvailableBookCopiesException("No available book copies for book id:" + bookId + " have been found.");
                }

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        if (bookCopiesToBeRented.size() > 0) {
            return rentLogRepository.save(new RentLog(bookCopiesToBeRented, user));
        }

        return new RentLog();
    }

    private boolean userHasNoActiveRentOfBook(long bookId, long userId) {
        return rentLogRepository.userHasNoActiveRentOfBook(bookId, userId);
    }

    @Transactional
    public List<RentLog> returnBookCopies(List<Long> bookCopyIds) {
        List<RentLog> associatedRentLogs = new ArrayList<>();

        for (long bookCopyId: bookCopyIds) {
            try {
                BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(
                        () -> new BookCopyNotFoundException("Book copy id:" + bookCopyId + " was not found.")
                );

                if (isRented(bookCopy)) {
                    // Get associated rentLog
                    long associatedRentLogId = rentLogRepository.findOpenRentLogIdWithBookCopyId(bookCopyId);
                    // return bookcopy
                    bookCopy.setRentStatus(RentStatus.AVAILABLE);
                    bookCopyDbService.updateBookCopyRentStatus(bookCopy);
                    // add to rentlog to associatedRentLogs
                    RentLog associatedRentLog = rentLogRepository.findById(associatedRentLogId).orElseThrow(
                            () -> new RentLogNotFound("Rent log id:" + associatedRentLogId + " was not found.")
                    );
                    associatedRentLog.returnBookCopy(bookCopy);

                    // if no more rented books under this rentlog - set return date
                    if (associatedRentLog.getBookCopies().isEmpty()) {
                        associatedRentLog.setReturnDate(LocalDateTime.now());
                    }
                    rentLogRepository.save(associatedRentLog);

                    associatedRentLogs.add(associatedRentLog);
                } else {
                    throw new BookCopyNotRentedException("Book copy id:" + bookCopyId + " is not currently rented!");
                }
            } catch (BookCopyNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // return List of rentlogs these books belonged to
        return associatedRentLogs;
    }

    private boolean isRented(BookCopy bookCopy) {
        return bookCopy.getRentStatus().equals(RentStatus.HIRED);
    }
}
