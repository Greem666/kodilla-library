package com.greem.kodillalibrary.domain.libraryuser;

import com.greem.kodillalibrary.domain.rentlog.RentLog;
import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class LibraryUserDto {
    private long id;
    private String firstName;
    private String lastName;
    private Date accountCreated;
    private List<RentLogDto> rentLogsDto;
}
