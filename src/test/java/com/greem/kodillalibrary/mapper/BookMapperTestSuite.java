package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
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
public class BookMapperTestSuite {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testMapToBook() {
        // Given
        BookDto bookDto = new BookDto(1, "title PH", "author PH", 2000, new ArrayList<BookCopyDto>());

        // When
        Book book = bookMapper.mapToBook(bookDto);

        long bookId = book.getId();
        String bookTitle = book.getTitle();
        String bookAuthor = book.getAuthor();
        int bookPublicationYear = book.getPublicationYear();
        List<BookCopy> bookCopies = book.getBookCopies();

        // Then
//        Assert.assertEquals(1, bookId);
        Assert.assertEquals("title PH", bookTitle);
        Assert.assertEquals("author PH", bookAuthor);
        Assert.assertEquals(2000, bookPublicationYear);
//        Assert.assertEquals(new ArrayList<BookCopy>(), bookCopies);
    }

    @Test
    public void testMapToBookDto() {
        // Given
        Book book = new Book(1, "title PH", "author PH", 2000, new ArrayList<BookCopy>());

        // When
        BookDto bookDto = bookMapper.mapToBookDto(book);

        long bookDtoId = bookDto.getId();
        String bookDtoTitle = bookDto.getTitle();
        String bookDtoAuthor = bookDto.getAuthor();
        int bookDtoPublicationYear = bookDto.getPublicationYear();
        List<BookCopyDto> bookDtoCopies = bookDto.getBookCopies();

        // Then
        Assert.assertEquals(1, bookDtoId);
        Assert.assertEquals("title PH", bookDtoTitle);
        Assert.assertEquals("author PH", bookDtoAuthor);
        Assert.assertEquals(2000, bookDtoPublicationYear);
        Assert.assertEquals(new ArrayList<BookCopyDto>(), bookDtoCopies);
    }

    @Test
    public void testMapToListOfBookDto() {
        // Given
        Book book1 = new Book(1, "title PH0", "author PH0", 2001, new ArrayList<BookCopy>());
        Book book2 = new Book(2, "title PH1", "author PH1", 2002, new ArrayList<BookCopy>());
        Book book3 = new Book(3, "title PH2", "author PH2", 2003, new ArrayList<BookCopy>());

        // When
        List<BookDto> listBookDto = bookMapper.mapToBookDtoList(Arrays.asList(book1, book2, book3));

        // Then
        int diff = 0;
        for (BookDto bookDto: listBookDto) {
            long bookDtoId = bookDto.getId();
            String bookDtoTitle = bookDto.getTitle();
            String bookDtoAuthor = bookDto.getAuthor();
            int bookDtoPublicationYear = bookDto.getPublicationYear();
            List<BookCopyDto> bookDtoCopies = bookDto.getBookCopies();

            Assert.assertEquals(1 + diff, bookDtoId);
            Assert.assertEquals("title PH" + diff, bookDtoTitle);
            Assert.assertEquals("author PH" + diff, bookDtoAuthor);
            Assert.assertEquals(2001 + diff, bookDtoPublicationYear);
            Assert.assertEquals(new ArrayList<BookCopyDto>(), bookDtoCopies);

            diff++;
        }
    }
}
