package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    @Autowired
    private BookCopyMapper bookCopyMapper;

    public Book mapToBook(BookDto bookDto) {
        List<BookCopyDto> bookCopyDtoList = Optional.ofNullable(bookDto.getBookCopiesDto()).orElse(new ArrayList<>());
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear(),
                bookCopyMapper.mapToBookCopyList(bookCopyDtoList)
        );
    }

    public BookDto mapToBookDto(Book book) {
        List<BookCopy> bookCopyList = Optional.ofNullable(book.getBookCopies()).orElse(new ArrayList<>());
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                bookCopyMapper.mapToBookCopyDtoList(bookCopyList)
        );
    }

    public List<BookDto> mapToBookDtoList(List<Book> listBooks) {
        return listBooks.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
