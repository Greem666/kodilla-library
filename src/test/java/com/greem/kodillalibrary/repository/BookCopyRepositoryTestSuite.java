package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyRepositoryTestSuite {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testBookCopy() {
        // Given
        Book book = new Book("Book Title", "Mr. Mackay", 1998);

        BookCopy copyAvailable = new BookCopy(book, RentStatus.AVAILABLE);
        BookCopy copyHired = new BookCopy(book, RentStatus.HIRED);
        BookCopy copyLost = new BookCopy(book, RentStatus.LOST);
        BookCopy copyDestroyed = new BookCopy(book, RentStatus.DESTROYED);

        bookCopyRepository.saveAll(Arrays.asList(copyAvailable, copyHired, copyLost, copyDestroyed));

        // When
        long copyAvailableId = copyAvailable.getId();
        long copyHiredId = copyHired.getId();
        long copyLostId = copyLost.getId();
        long copyDestroyedId = copyDestroyed.getId();
        long bookId = book.getId();

        // Then
        List<Long> copyIds = Arrays.asList(copyAvailableId, copyHiredId, copyLostId, copyDestroyedId);
        List<BookCopy> bookCopies = Arrays.asList(copyAvailable, copyHired, copyLost, copyDestroyed);

        for (int i = 0; i < copyIds.size(); i++) {
            long bookCopyId = copyIds.get(i);
            BookCopy createdBookCopy = bookCopies.get(i);
            BookCopy retrievedBookCopy = bookCopyRepository.findById(bookCopyId).orElse(new BookCopy());

            Assert.assertEquals(createdBookCopy, retrievedBookCopy);
            Assert.assertEquals(book, retrievedBookCopy.getBook());
        }

        // Clean-up
        try {
            for (long copyId: Arrays.asList(copyAvailableId, copyHiredId, copyLostId, copyDestroyedId)) {
                bookCopyRepository.deleteById(copyId);
            }
            bookRepository.deleteById(bookId);
        } catch (Exception e) {
            // Nothing
        }
    }
}
