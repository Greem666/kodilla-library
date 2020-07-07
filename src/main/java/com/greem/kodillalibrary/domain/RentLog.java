package com.greem.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "RENT_LOGS")
public class RentLog {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "LIBRARY_USER_ID")
    private LibraryUser libraryUser;

    @NotNull
    @Column(name = "RENT_DATE")
    private LocalDate rentDate = LocalDate.now();

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    public RentLog(BookCopy bookCopy, LibraryUser libraryUser) {
        setBookCopy(bookCopy);
        setLibraryUser(libraryUser);
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
        bookCopy.getRentLogs().add(this);
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
        libraryUser.getRentLogs().add(this);
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
