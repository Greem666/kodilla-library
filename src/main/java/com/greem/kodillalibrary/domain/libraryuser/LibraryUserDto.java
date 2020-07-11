package com.greem.kodillalibrary.domain.libraryuser;

import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import lombok.*;

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
    private Date accountCreated;
    @EqualsAndHashCode.Exclude
    private List<RentLogDto> rentLogsDto = new ArrayList<>();

    public LibraryUserDto(long id, String firstName, String lastName, Date accountCreated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreated = accountCreated;
    }

    public LibraryUserDto(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public LibraryUserDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
