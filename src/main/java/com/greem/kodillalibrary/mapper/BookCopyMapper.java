package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RentLogMapper rentLogMapper;

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) {
        return new BookCopy(
                bookCopyDto.getId(),
                bookMapper.mapToBook(bookCopyDto.getBookDto()),
                bookCopyDto.getRentStatus(),
                rentLogMapper.mapToListOfRentLog(bookCopyDto.getRentLogsDto())
        );
    }

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookMapper.mapToBookDto(bookCopy.getBook()),
                bookCopy.getRentStatus(),
                rentLogMapper.mapToListOfRentLogDto(bookCopy.getRentLogs())
        );
    }

    public List<BookCopyDto> mapToListOfBookCopiesDto(List<BookCopy> listBookCopies) {
        return listBookCopies.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }

    public List<BookCopy> mapToListOfBookCopies(List<BookCopyDto> listBookCopiesDto) {
        return listBookCopiesDto.stream()
                .map(this::mapToBookCopy)
                .collect(Collectors.toList());
    }
}
