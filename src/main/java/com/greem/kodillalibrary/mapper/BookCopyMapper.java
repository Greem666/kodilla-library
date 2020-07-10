package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
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

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
        BookDto bookDto = Optional.ofNullable(bookCopyDto.getBookDto()).orElse(new BookDto());
        List<RentLogDto> rentLogDtoList = Optional.ofNullable(bookCopyDto.getRentLogsDto()).orElse(new ArrayList<>());
        return new BookCopy(
                bookCopyDto.getId(),
                bookMapper.mapToBook(bookDto),
                bookCopyDto.getRentStatus(),
                rentLogMapper.mapToRentLogList(rentLogDtoList)
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        Book book = Optional.ofNullable(bookCopy.getBook()).orElse(new Book());
        List<RentLog> rentLogList = Optional.ofNullable(bookCopy.getRentLogs()).orElse(new ArrayList<>());
        return new BookCopyDto(
                bookCopy.getId(),
                bookMapper.mapToBookDto(book),
                bookCopy.getRentStatus(),
                rentLogMapper.mapToRentLogDtoList(rentLogList)
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
