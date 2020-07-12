package com.greem.kodillalibrary.service;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.exceptions.BookNotFoundException;
import com.greem.kodillalibrary.repository.BookCopyRepository;
import com.greem.kodillalibrary.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookCopyDbService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        try {
            long bookId = bookCopy.getBook().getId();
            bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book by id:" + bookId + " was not found."));

            return bookCopyRepository.save(bookCopy);

        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return new BookCopy();
    }

    public int updateBookCopyRentStatus(BookCopy bookCopy) {
        return bookCopyRepository.updateRentStatus(bookCopy.getId(), bookCopy.getRentStatus());
    }

    public List<BookCopy> findAvailableBookCopies(long bookId) {
        return bookCopyRepository.findAvailableBookCopies(bookId);
    }

    public int countAvailableBookCopies(long bookId) {
        return bookCopyRepository.findAvailableBookCopies(bookId).size();
    }
}
