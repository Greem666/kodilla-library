package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentLogMapper {
    @Autowired
    private BookCopyMapper bookCopyMapper;
    @Autowired
    private LibraryUserMapper libraryUserMapper;

    public RentLog mapToRentLog(RentLogDto rentLogDto) {
        return new RentLog(
                rentLogDto.getId(),
                bookCopyMapper.mapToBookCopyList(rentLogDto.getBookCopiesDto()),
                libraryUserMapper.mapToLibraryUser(rentLogDto.getLibraryUserDto()),
                rentLogDto.getRentDate(),
                rentLogDto.getReturnDate()
        );
    }

    public RentLogDto mapToRentLogDto(RentLog rentLog) {
        return new RentLogDto(
                rentLog.getId(),
                bookCopyMapper.mapToBookCopyDtoList(rentLog.getBookCopies()),
                libraryUserMapper.mapToLibraryUserDto(rentLog.getLibraryUser()),
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