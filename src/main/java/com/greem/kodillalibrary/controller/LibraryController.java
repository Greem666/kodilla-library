package com.greem.kodillalibrary.controller;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import com.greem.kodillalibrary.mapper.BookCopyMapper;
import com.greem.kodillalibrary.mapper.BookMapper;
import com.greem.kodillalibrary.mapper.LibraryUserMapper;
import com.greem.kodillalibrary.service.BookCopyDbService;
import com.greem.kodillalibrary.service.BookDbService;
import com.greem.kodillalibrary.service.LibraryUserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library/")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryUserDbService libraryUserDbService;
    private final LibraryUserMapper libraryUserMapper;
    private final BookDbService bookDbService;
    private final BookMapper bookMapper;
    private final BookCopyDbService bookCopyDbService;
    private final BookCopyMapper bookCopyMapper;

    @RequestMapping(method = RequestMethod.POST, value = "addLibraryUser", consumes = APPLICATION_JSON_VALUE)
    public LibraryUser addLibraryUser(@RequestBody LibraryUserDto libraryUserDto) {
        return libraryUserDbService.saveUser(libraryUserMapper.mapToLibraryUser(libraryUserDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public Book addBook(@RequestBody BookDto bookDto) {
        return bookDbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public BookCopy addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        return bookCopyDbService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }
}
