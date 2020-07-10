package com.greem.kodillalibrary.domain.libraryuser;

import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import lombok.*;

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
    private Date accountCreated;
    @EqualsAndHashCode.Exclude
    private List<RentLogDto> rentLogsDto;
}
