package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.exceptions.BookNotFoundException;
import com.greem.kodillalibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RentLogMapper rentLogMapper;

    @Autowired
    private BookRepository bookRepository;

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
//        long bookId = bookCopyDto.getBook().getId();
//        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book id:" + bookId + " not found."));

        return new BookCopy(
                bookCopyDto.getId(),
//                book,
                bookMapper.mapToBook(bookCopyDto.getBook()),
                bookCopyDto.getRentStatus(),
                rentLogMapper.mapToRentLogList(bookCopyDto.getRentLogs())
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        Book book = Optional.ofNullable(bookCopy.getBook()).orElse(new Book());
        List<RentLog> rentLogList = Optional.ofNullable(bookCopy.getRentLogs()).orElse(new ArrayList<>());

        return new BookCopyDto(
                bookCopy.getId(),
                bookMapper.mapToBookDto(book),
                bookCopy.getRentStatus(),
                rentLogMapper.mapToRentLogDtoList(bookCopy.getRentLogs())
        );
    }

    public List<BookCopyDto> mapToBookCopyDtoList(List<BookCopy> listBookCopies) {
        return listBookCopies.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }

    public List<BookCopy> mapToBookCopyList(List<BookCopyDto> listBookCopiesDto) {
        return listBookCopiesDto.stream()
                .map(this::mapToBookCopy)
                .collect(Collectors.toList());
    }
}
