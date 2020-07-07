package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.Book;
import com.greem.kodillalibrary.domain.BookCopy;
import com.greem.kodillalibrary.domain.LibraryUser;
import com.greem.kodillalibrary.domain.RentLog;
import com.greem.kodillalibrary.domain.enums.RentStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;

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

        BookCopy bookCopy1 = new BookCopy(book, RentStatus.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(book, RentStatus.AVAILABLE);

        RentLog rentLog1 = new RentLog(bookCopy1, user1);
        RentLog rentLog2 = new RentLog(bookCopy2, user2);

        rentLog2.setReturnDate(LocalDate.of(2020, 7, 31));

        rentLogRepository.save(rentLog1);
        rentLogRepository.save(rentLog2);

        // When
        long rentLog1Id = rentLog1.getId();
        long rentLog2Id = rentLog2.getId();

        RentLog retrievedRentLog1 = rentLogRepository.findById(rentLog1Id).orElse(new RentLog());
        RentLog retrievedRentLog2 = rentLogRepository.findById(rentLog2Id).orElse(new RentLog());

        // Then
        Assert.assertEquals(rentLog1, retrievedRentLog1);
        Assert.assertEquals(bookCopy1, retrievedRentLog1.getBookCopy());
        Assert.assertEquals(book, retrievedRentLog1.getBookCopy().getBook());
        Assert.assertEquals(user1, retrievedRentLog1.getLibraryUser());

        Assert.assertEquals(rentLog2, retrievedRentLog2);
        Assert.assertEquals(bookCopy2, retrievedRentLog2.getBookCopy());
        Assert.assertEquals(book, retrievedRentLog2.getBookCopy().getBook());
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
