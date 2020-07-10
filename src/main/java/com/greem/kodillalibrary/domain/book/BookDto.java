package com.greem.kodillalibrary.domain.book;

import com.greem.kodillalibrary.domain.bookcopy.BookCopyDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class BookDto {

    private long id;
    private String title;
    private String author;
    private int publicationYear;
    @EqualsAndHashCode.Exclude
    private List<BookCopyDto> bookCopiesDto;
}
