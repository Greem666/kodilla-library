package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
                rentLogMapper.mapToRentLogList(libraryUserDto.getRentLogsDto())
        );
    }

    public LibraryUserDto mapToLibraryUserDto(LibraryUser libraryUser) {
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
