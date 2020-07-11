package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class RentLogRepositoryTestSuite {
    @Autowired
    private RentLogRepository rentLogRepository;

    @Test
    public void testRentLogRepository() {
        // Given
        LibraryUser user1 = new LibraryUser("Matt", "Green");
        LibraryUser user2 = new LibraryUser("Jo", "Pink");

        Book book = new Book("Book title", "Mr. Mackay", 1998);
        Book book2 = new Book("Book title2", "Mr. Mackay", 1998);

        BookCopy bookCopy1 = new BookCopy(book, RentStatus.HIRED);
        BookCopy bookCopy2 = new BookCopy(book, RentStatus.HIRED);
        BookCopy bookCopy3 = new BookCopy(book2, RentStatus.AVAILABLE);
        BookCopy bookCopy4 = new BookCopy(book2, RentStatus.AVAILABLE);

        RentLog rentLog1 = new RentLog(Arrays.asList(bookCopy1, bookCopy2), user1);
        RentLog rentLog2 = new RentLog(Arrays.asList(bookCopy3, bookCopy4), user2);

        rentLog2.setReturnDate(LocalDate.of(2020, 7, 31));

        rentLogRepository.save(rentLog1);
        rentLogRepository.save(rentLog2);

        // When
        long rentLog1Id = rentLog1.getId();
        long rentLog2Id = rentLog2.getId();

        RentLog retrievedRentLog1 = rentLogRepository.findById(rentLog1Id).orElse(new RentLog());
        RentLog retrievedRentLog2 = rentLogRepository.findById(rentLog2Id).orElse(new RentLog());

        // Then
        // RENTLOG1
        Assert.assertEquals(rentLog1, retrievedRentLog1);
        Assert.assertTrue(retrievedRentLog1.getBookCopies().containsAll(Arrays.asList(bookCopy1, bookCopy2)));
        Assert.assertTrue(
                retrievedRentLog1.getBookCopies().stream()
                        .map(BookCopy::getBook)
                        .collect(Collectors.toList()).contains(book)
        );
        Assert.assertFalse(
                retrievedRentLog1.getBookCopies().stream()
                        .map(BookCopy::getBook)
                        .collect(Collectors.toList()).contains(book2)
        );
        Assert.assertEquals(user1, retrievedRentLog1.getLibraryUser());

        // RENTLOG2
        Assert.assertEquals(rentLog2, retrievedRentLog2);
        Assert.assertTrue(retrievedRentLog2.getBookCopies().containsAll(Arrays.asList(bookCopy3, bookCopy4)));
        Assert.assertTrue(
                retrievedRentLog2.getBookCopies().stream()
                        .map(BookCopy::getBook)
                        .collect(Collectors.toList()).contains(book2)
        );
        Assert.assertFalse(
                retrievedRentLog2.getBookCopies().stream()
                        .map(BookCopy::getBook)
                        .collect(Collectors.toList()).contains(book)
        );
        Assert.assertEquals(user2, retrievedRentLog2.getLibraryUser());

        // Clean-up
        try {
            rentLogRepository.deleteById(rentLog1Id);
            rentLogRepository.deleteById(rentLog2Id);
        } catch (Exception e) {
            // Nothing
        }
    }

}
