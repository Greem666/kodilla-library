package com.greem.kodillalibrary.domain;

import com.greem.kodillalibrary.domain.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopy {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TITLE_ID")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "RENT_STATUS")
    private RentStatus rentStatus;

    @EqualsAndHashCode.Exclude
    @OneToMany(
            targetEntity = RentLog.class,
            mappedBy = "bookCopy",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
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
