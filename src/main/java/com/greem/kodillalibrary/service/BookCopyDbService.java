package com.greem.kodillalibrary.service;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookCopyDbService {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public int updateBookCopyRentStatus(BookCopy bookCopy) {
        return bookCopyRepository.updateRentStatus(bookCopy.getId(), bookCopy.getRentStatus());
    }

    public int countAvailableBookCopies(String title) {
        return bookCopyRepository.countAvailableBookCopies(title);
    }
}
