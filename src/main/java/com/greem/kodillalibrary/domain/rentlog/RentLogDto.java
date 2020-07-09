package com.greem.kodillalibrary.domain.rentlog;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class RentLogDto {
    private long id;
    private List<BookCopyDto> bookCopiesDto;
    private LibraryUserDto libraryUserDto;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
