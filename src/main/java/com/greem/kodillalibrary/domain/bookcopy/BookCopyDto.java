package com.greem.kodillalibrary.domain.bookcopy;

import com.greem.kodillalibrary.domain.book.BookDto;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import com.greem.kodillalibrary.domain.rentlog.RentLogDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class BookCopyDto {
    private long id;
    private BookDto bookDto;
    private RentStatus rentStatus;
    @EqualsAndHashCode.Exclude
    private List<RentLogDto> rentLogsDto;
}
