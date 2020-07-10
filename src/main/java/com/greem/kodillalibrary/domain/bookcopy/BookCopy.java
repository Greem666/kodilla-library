package com.greem.kodillalibrary.domain.bookcopy;

import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//@NamedQuery(
//        name = "BookCopy.findAllBookCopiesByTitle",
//        query = "FROM BookCopy" +
//                "WHERE book.title = :TITLE"
//)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TITLE_ID")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "RENT_STATUS")
    private RentStatus rentStatus;

    @EqualsAndHashCode.Exclude
    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "bookCopies"
    )
    private List<RentLog> rentLogs = new ArrayList<>();

    public BookCopy(Book book, RentStatus rentStatus) {
        setBook(book);
        this.rentStatus = rentStatus;
    }

    public void setBook(Book book) {
        this.book = book;
        book.getBookCopies().add(this);
    }
}
