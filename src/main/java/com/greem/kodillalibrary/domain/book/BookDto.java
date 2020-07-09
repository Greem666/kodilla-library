package com.greem.kodillalibrary.domain.book;

import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class BookDto {

    private long id;
    private String title;
    private String author;
    private int publicationYear;
    private List<BookCopyDto> bookCopiesDto;
}
