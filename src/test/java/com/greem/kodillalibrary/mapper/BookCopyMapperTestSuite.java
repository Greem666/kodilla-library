package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyMapperTestSuite {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Test
    public void testMapToBookCopy() {
        // Given
        BookDto bookDto = new BookDto(1, "title PH", "author PH", 2000, new ArrayList<BookCopyDto>());
        BookCopyDto bookCopyDto = new BookCopyDto(2, bookDto, RentStatus.AVAILABLE, new ArrayList<RentLogDto>());

        // When
        Book book = bookMapper.mapToBook(bookDto);
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);

        long bookCopyId = bookCopy.getId();
        Book bookCopyBook = bookCopy.getBook();
        RentStatus bookCopyRentStatus = bookCopy.getRentStatus();
        List<RentLog> bookCopyRentLogList = bookCopy.getRentLogs();

        // Then
        Assert.assertEquals(2, bookCopyId);
        Assert.assertEquals(book, bookCopyBook);
        Assert.assertEquals(RentStatus.AVAILABLE, bookCopyRentStatus);
        Assert.assertEquals(new ArrayList<RentLog>(), bookCopyRentLogList);
    }

    @Test
    public void testMapToBookCopyDto() {
        // Given
        Book book = new Book(1, "title PH", "author PH", 2000, new ArrayList<BookCopy>());
        BookCopy bookCopy = new BookCopy(2, book, RentStatus.AVAILABLE, new ArrayList<RentLog>());

        // When
        BookDto bookDto = bookMapper.mapToBookDto(book);
        BookCopyDto bookCopyDto = bookCopyMapper.mapToBookCopyDto(bookCopy);

        long bookCopyDtoId = bookCopyDto.getId();
        BookDto bookCopyDtoBook = bookCopyDto.getBookDto();
        RentStatus bookCopyDtoRentStatus = bookCopyDto.getRentStatus();
        List<RentLogDto> bookCopyDtoRentLogList = bookCopyDto.getRentLogsDto();

        // Then
        Assert.assertEquals(2, bookCopyDtoId);
        Assert.assertEquals(bookDto, bookCopyDtoBook);
        Assert.assertEquals(RentStatus.AVAILABLE, bookCopyDtoRentStatus);
        Assert.assertEquals(new ArrayList<RentLogDto>(), bookCopyDtoRentLogList);
    }

    @Test
    public void testMapToListOfBookDto() {
        // Given
        Book book = new Book(1, "title PH0", "author PH0", 2001, new ArrayList<BookCopy>());

        BookCopy bookCopy1 = new BookCopy(1, book, RentStatus.AVAILABLE, new ArrayList<RentLog>());
        BookCopy bookCopy2 = new BookCopy(2, book, RentStatus.HIRED, new ArrayList<RentLog>());
        BookCopy bookCopy3 = new BookCopy(3, book, RentStatus.LOST, new ArrayList<RentLog>());
        BookCopy bookCopy4 = new BookCopy(4, book, RentStatus.DESTROYED, new ArrayList<RentLog>());

        // When
        BookDto bookDto = bookMapper.mapToBookDto(book);
        List<BookCopyDto> listBookCopiesDto = bookCopyMapper.mapToBookCopyDtoList(
                Arrays.asList(bookCopy1, bookCopy2, bookCopy3, bookCopy4)
        );

        // Then
        int i = 0;
        List<RentStatus> orderedRentStatusList = Arrays.asList(RentStatus.AVAILABLE, RentStatus.HIRED, RentStatus.LOST, RentStatus.DESTROYED);

        for (BookCopyDto bookCopyDto: listBookCopiesDto) {
            long bookCopyDtoId = bookCopyDto.getId();
            BookDto bookCopyDtoBook = bookCopyDto.getBookDto();
            RentStatus bookCopyDtoRentStatus = bookCopyDto.getRentStatus();
            List<RentLogDto> bookCopyDtoRentLogDtoList = bookCopyDto.getRentLogsDto();

            Assert.assertEquals(1 + i, bookCopyDtoId);
            Assert.assertEquals(bookDto, bookCopyDtoBook);
            Assert.assertEquals(orderedRentStatusList.get(i), bookCopyDtoRentStatus);
            Assert.assertEquals(new ArrayList<RentLogDto>(), bookCopyDtoRentLogDtoList);

            i++;
        }
    }
}
