package com.greem.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Year;
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

    @EqualsAndHashCode.Exclude
    @OneToMany(
            targetEntity = BookCopy.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
//            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopies = new ArrayList<>();

    public Book(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.publicationYear = LocalDate.of(yearOfPublication, 1, 1).getYear();
    }
}
