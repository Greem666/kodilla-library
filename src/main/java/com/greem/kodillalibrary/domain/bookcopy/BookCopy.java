package com.greem.kodillalibrary.domain.bookcopy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.greem.kodillalibrary.domain.book.Book;
import com.greem.kodillalibrary.domain.bookcopy.enums.RentStatus;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @JsonManagedReference
    @NotNull
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TITLE_ID")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "RENT_STATUS")
    private RentStatus rentStatus;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "bookCopies"
    )
    private List<RentLog> rentLogs = new ArrayList<>();

    public BookCopy(Book book, RentStatus rentStatus) {
        this.book = book;
        this.rentStatus = rentStatus;
    }

    public void setBook(Book book) {
        this.book = book;
        book.getBookCopies().add(this);
    }
}

