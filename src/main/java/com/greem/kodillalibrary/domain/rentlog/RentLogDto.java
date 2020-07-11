package com.greem.kodillalibrary.domain.rentlog;

import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class RentLogDto {
    private long id;
    @EqualsAndHashCode.Exclude
    private List<BookCopyDto> bookCopiesDto;
    private LibraryUserDto libraryUserDto;
    @EqualsAndHashCode.Exclude
    private LocalDate rentDate = LocalDate.now();
    @EqualsAndHashCode.Exclude
    private LocalDate returnDate;
}
