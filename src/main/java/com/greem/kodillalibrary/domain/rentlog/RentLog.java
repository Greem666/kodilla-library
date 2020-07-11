package com.greem.kodillalibrary.domain.rentlog;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "RENT_LOGS")
public class RentLog {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_RENT_LOGS_BOOK_COPIES",
            joinColumns = {@JoinColumn(name = "RENTLOG_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOKCOPY_ID", referencedColumnName = "ID")}
    )
    private List<BookCopy> bookCopies = new ArrayList<>();

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "LIBRARY_USER_ID")
    private LibraryUser libraryUser;

    @Column(name = "RENT_DATE")
    private LocalDate rentDate = LocalDate.now();

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    public RentLog(long id, List<BookCopy> bookCopies, LibraryUser libraryUser) {
        this.id = id;
        for (BookCopy bookCopy: bookCopies) {
            addBookCopy(bookCopy);
        }
        setLibraryUser(libraryUser);
    }

    public RentLog(List<BookCopy> bookCopies, LibraryUser libraryUser) {
        for (BookCopy bookCopy: bookCopies) {
            addBookCopy(bookCopy);
        }
        setLibraryUser(libraryUser);
    }

    public void addBookCopy(BookCopy bookCopy) {
        this.bookCopies.add(bookCopy);
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
