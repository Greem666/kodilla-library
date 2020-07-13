package com.greem.kodillalibrary.domain.libraryuser;

import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class LibraryUserDto {
    private long id;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude
    private LocalDateTime accountCreated;
    @EqualsAndHashCode.Exclude
    private List<RentLogDto> rentLogs = new ArrayList<>();

    public LibraryUserDto(long id, String firstName, String lastName, LocalDateTime accountCreated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreated = accountCreated;
    }
}
