package com.greem.kodillalibrary.domain.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greem.kodillalibrary.domain.bookcopy.BookCopy;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "AUTHOR")
    private String author;

    @NotNull
    @Column(name = "PUBLICATION_YEAR")
    private int publicationYear;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(
            targetEntity = BookCopy.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
//            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopies = new ArrayList<>();

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = LocalDate.of(publicationYear, 1, 1).getYear();
    }

    public Book(long id, String title, String author, int publicationYear) {
        this(title, author, publicationYear);
        this.id = id;
    }
}
