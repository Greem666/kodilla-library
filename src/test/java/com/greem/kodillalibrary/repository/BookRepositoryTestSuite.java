package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.book.Book;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTestSuite {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testBook() {
        // Given
        Book book = new Book("Book title", "Mr. Mackay", 1998);
        bookRepository.save(book);

        // When
        long id = book.getId();

        // Then
        Assert.assertEquals(book, bookRepository.findById(id).orElse(new Book()));

        // Clean-up
        bookRepository.deleteById(id);
    }
}
