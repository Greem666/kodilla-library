package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    @Autowired
    private BookCopyMapper bookCopyMapper;

    public Book mapToBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear(),
                bookCopyMapper.mapToListOfBookCopies(bookDto.getBookCopiesDto())
        );
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                bookCopyMapper.mapToListOfBookCopiesDto(book.getBookCopies())
        );
    }

    public List<BookDto> mapToListOfBookDto(List<Book> listBooks) {
        return listBooks.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
