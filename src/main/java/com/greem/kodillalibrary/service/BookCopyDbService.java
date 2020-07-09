package com.greem.kodillalibrary.service;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookCopyDbService {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

//    public List<BookCopy> findAllBookCopiesByTitle(String title) {
//        return bookCopyRepository.findAllBookCopiesByTitle(title);
//    }
}
