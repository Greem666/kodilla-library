package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import com.greem.kodillalibrary.repository.RentLogRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class RentLogMapperTestSuite {
    @Autowired
    private RentLogMapper rentLogMapper;
    @Autowired
    private RentLogRepository rentLogRepository;

    private LibraryUser libraryUser;
    private Book book;
    private BookCopy bookCopy;
    private RentLog rentLog;
    private RentLog rentLog2;
    private List<RentLog> rentLogList;

    private LibraryUserDto libraryUserDto;
    private BookDto bookDto;
    private BookCopyDto bookCopyDto;
    private RentLogDto rentLogDto;
    private RentLogDto rentLogDto2;
    private List<RentLogDto> rentLogDtoList;

    @Before
    public void beforeEachTest() {
        // Entity objects
        libraryUser = new LibraryUser(1, "John", "Doe", LocalDateTime.now(), new ArrayList<>());

        book = new Book(2, "test title", "test author", 1998, new ArrayList<>());

        bookCopy = new BookCopy(3, book, RentStatus.HIRED, new ArrayList<>());
//        bookCopy.setBook(book);
//        book.getBookCopies().add(bookCopy);

        rentLog = new RentLog(4, Collections.singletonList(bookCopy), libraryUser, LocalDate.now(), null);
        rentLog2 = new RentLog(5, Collections.singletonList(bookCopy), libraryUser, LocalDate.now(), null);

        rentLogList = Arrays.asList(rentLog, rentLog2);

        // DTO objects
        libraryUserDto = new LibraryUserDto(1, "John", "Doe", LocalDateTime.now(), new ArrayList<>());

        bookDto = new BookDto(2, "test title", "test author", 1998, new ArrayList<>());

        bookCopyDto = new BookCopyDto(3, bookDto, RentStatus.HIRED, new ArrayList<>());
//        bookCopyDto.setBookDto(bookDto);
//        bookDto.getBookCopiesDto().add(bookCopyDto);

        rentLogDto = new RentLogDto(4, Collections.singletonList(bookCopyDto), libraryUserDto, LocalDate.now(), null);
        rentLogDto2 = new RentLogDto(5, Collections.singletonList(bookCopyDto), libraryUserDto, LocalDate.now(), null);

        rentLogDtoList = Arrays.asList(rentLogDto, rentLogDto2);
    }

    @Test
    public void testMapToRentLog() {
        // Given
        // --

        // When
        RentLog mappedRentLog = rentLogMapper.mapToRentLog(rentLogDto);

        // Then
        Assert.assertEquals(rentLog.getLibraryUser().getFirstName(), mappedRentLog.getLibraryUser().getFirstName());
        Assert.assertEquals(rentLog.getLibraryUser().getLastName(), mappedRentLog.getLibraryUser().getLastName());
        Assert.assertEquals(rentLog.getBookCopies().size(), mappedRentLog.getBookCopies().size());
    }

    @Test
    public void testMapToRentLogDto() {
        // Given
        // --

        // When
        RentLogDto mappedRentLogDto = rentLogMapper.mapToRentLogDto(rentLog);

        // Then
        Assert.assertEquals(rentLogDto, mappedRentLogDto);
    }

    @Test
    public void testMapToRentLogList() {
        // Given
        // -

        // When
        List<RentLog> mappedRentLogList = rentLogMapper.mapToRentLogList(rentLogDtoList);

        // Then
        Assert.assertEquals(rentLogList.size(), mappedRentLogList.size());
        for (int i = 0; i < mappedRentLogList.size(); i++) {
            RentLog mapperRentLog = mappedRentLogList.get(i);
            RentLog rentLog = rentLogList.get(i);
            
            Assert.assertEquals(rentLog.getLibraryUser().getFirstName(), mapperRentLog.getLibraryUser().getFirstName());
            Assert.assertEquals(rentLog.getLibraryUser().getLastName(), mapperRentLog.getLibraryUser().getLastName());
            Assert.assertEquals(rentLog.getBookCopies().size(), mapperRentLog.getBookCopies().size());
        }
    }

    @Test
    public void testMapToRentLogDtoList() {
        // Given
        // -

        // When
        List<RentLogDto> mappedRentLogDtoList = rentLogMapper.mapToRentLogDtoList(rentLogList);

        // Then
        Assert.assertEquals(rentLogDtoList.size(), mappedRentLogDtoList.size());
        for (RentLogDto rentLogDto: mappedRentLogDtoList) {
            Assert.assertTrue(rentLogDtoList.contains(rentLogDto));
        }
    }
}
