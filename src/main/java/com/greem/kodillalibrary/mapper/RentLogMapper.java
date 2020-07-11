package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RentLogMapper {
    @Autowired
    private BookCopyMapper bookCopyMapper;
    @Autowired
    private LibraryUserMapper libraryUserMapper;

    public RentLog mapToRentLog(RentLogDto rentLogDto) {
        List<BookCopyDto> bookCopyDtoList = Optional.ofNullable(rentLogDto.getBookCopiesDto()).orElse(new ArrayList<>());
        LibraryUserDto libraryUserDto = Optional.ofNullable(rentLogDto.getLibraryUserDto()).orElse(new LibraryUserDto());
        return new RentLog(
                rentLogDto.getId(),
                bookCopyMapper.mapToBookCopyList(bookCopyDtoList),
                libraryUserMapper.mapToLibraryUser(libraryUserDto)
        );
    }

    public RentLogDto mapToRentLogDto(RentLog rentLog) {
        List<BookCopy> bookCopyList = Optional.ofNullable(rentLog.getBookCopies()).orElse(new ArrayList<>());
        LibraryUser libraryUser = Optional.ofNullable(rentLog.getLibraryUser()).orElse(new LibraryUser());
        return new RentLogDto(
                rentLog.getId(),
                bookCopyMapper.mapToBookCopyDtoList(bookCopyList),
                libraryUserMapper.mapToLibraryUserDto(libraryUser),
                rentLog.getRentDate(),
                rentLog.getReturnDate()
        );
    }

    public List<RentLogDto> mapToRentLogDtoList(List<RentLog> listRentLogs) {
        return listRentLogs.stream()
                .map(this::mapToRentLogDto)
                .collect(Collectors.toList());
    }

    public List<RentLog> mapToRentLogList(List<RentLogDto> listRentLogsDto) {
        return listRentLogsDto.stream()
                .map(this::mapToRentLog)
                .collect(Collectors.toList());
    }
}
