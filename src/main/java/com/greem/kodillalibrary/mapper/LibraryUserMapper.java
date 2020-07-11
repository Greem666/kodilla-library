package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LibraryUserMapper {

    @Autowired
    private RentLogMapper rentLogMapper;

    public LibraryUser mapToLibraryUser(LibraryUserDto libraryUserDto) {
        return new LibraryUser(
                libraryUserDto.getId(),
                libraryUserDto.getFirstName(),
                libraryUserDto.getLastName(),
                libraryUserDto.getAccountCreated(),
                rentLogMapper.mapToRentLogList(libraryUserDto.getRentLogs())
        );
    }

    public LibraryUserDto mapToLibraryUserDto(LibraryUser libraryUser) {
        List<RentLog> rentLogList = Optional.ofNullable(libraryUser.getRentLogs()).orElse(new ArrayList<>());
        return new LibraryUserDto(
                libraryUser.getId(),
                libraryUser.getFirstName(),
                libraryUser.getLastName(),
                libraryUser.getAccountCreated(),
                rentLogMapper.mapToRentLogDtoList(libraryUser.getRentLogs())
        );
    }

    public List<LibraryUserDto> mapToLibraryUserDtoList(List<LibraryUser> listLibraryUsers) {
        return listLibraryUsers.stream()
                .map(this::mapToLibraryUserDto)
                .collect(Collectors.toList());
    }
}
