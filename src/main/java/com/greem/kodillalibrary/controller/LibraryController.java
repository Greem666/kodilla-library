package com.greem.kodillalibrary.controller;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.mapper.BookCopyMapper;
import com.greem.kodillalibrary.mapper.BookMapper;
import com.greem.kodillalibrary.mapper.LibraryUserMapper;
import com.greem.kodillalibrary.service.BookCopyDbService;
import com.greem.kodillalibrary.service.BookDbService;
import com.greem.kodillalibrary.service.LibraryUserDbService;
import com.greem.kodillalibrary.service.RentLogDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private final RentLogDbService rentLogDbService;

    @RequestMapping(method = RequestMethod.POST, value = "addLibraryUser", consumes = APPLICATION_JSON_VALUE)
    public LibraryUser addLibraryUser(@RequestBody LibraryUserDto libraryUserDto) {
        return libraryUserDbService.saveUser(libraryUserMapper.mapToLibraryUser(libraryUserDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public Book addBook(@RequestBody BookDto bookDto) {
        return bookDbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    //TODO: Prevent saving of BookCopyDtos, which point to non-existing Book records
    @RequestMapping(method = RequestMethod.POST, value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public BookCopy addBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        bookCopyDto.setRentStatus(RentStatus.AVAILABLE);  // Freshly added copies should always be available! Should that logic be here?
        return bookCopyDbService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changeBookCopyRentStatus", consumes = APPLICATION_JSON_VALUE)
    public int changeBookCopyRentStatus(@RequestBody BookCopyDto bookCopyDto) {
        return bookCopyDbService.updateBookCopyRentStatus(bookCopyMapper.mapToBookCopy(bookCopyDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "countAvailableBookCopies")
    public int countAvailableBookCopies(@RequestParam long bookId) {
        return bookCopyDbService.countAvailableBookCopies(bookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "rentBooks")
    public RentLog rentBooks(@RequestParam List<Long> bookIds, @RequestParam long userId) {
        return rentLogDbService.rentBooks(bookIds, userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "returnBookCopies")
    public List<RentLog> returnBookCopies(@RequestParam List<Long> bookCopyIds) {
        return rentLogDbService.returnBookCopies(bookCopyIds);
    }
}
