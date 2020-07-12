package com.greem.kodillalibrary.domain.rentlog;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "LIBRARY_USER_ID")
    private LibraryUser libraryUser;

    @CreationTimestamp
    @Column(name = "RENT_DATE")
    private LocalDateTime rentDate;

    @Column(name = "RETURN_DATE")
    private LocalDateTime returnDate;

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

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void returnBookCopy(BookCopy bookCopy) {
        bookCopies.remove(bookCopy);
    }
}
